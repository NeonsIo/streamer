package io.neons.streamer

import java.net.{InetAddress, InetSocketAddress}
import java.text.SimpleDateFormat
import java.util
import java.util.{Calendar, Date, TimeZone}

import io.neons.common.log.{HeaderBag, Log}
import io.neons.common.session.Session
import io.neons.streamer.collector.SessionCollector
import io.neons.streamer.environment.ExecutionEnvironment
import io.neons.streamer.event.builder.EventBuilder
import io.neons.streamer.sink.{Elasticsearch5Sink, Elasticsearch5SinkFunction}
import io.neons.streamer.source.KafkaConsumer
import org.apache.flink.api.common.functions.{MapFunction, RichFlatMapFunction, RuntimeContext}
import org.apache.flink.api.common.state.{FoldingState, FoldingStateDescriptor}
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.ContinuousEventTimeTrigger
import org.apache.flink.util.Collector
import org.apache.flink.configuration.Configuration
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.{Client, Requests}
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Application {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = ExecutionEnvironment.create

    val sessionDataStream = env
      .addSource(KafkaConsumer.create)
      .map(log => EventBuilder build log)
      .keyBy(_.userTrackerId)
      .keyBy(_.userVisitorId)
      .window(EventTimeSessionWindows withGap Time.minutes(30))
      .trigger(ContinuousEventTimeTrigger of Time.seconds(30))
      .apply(SessionCollector.collect)

    sessionDataStream.print()

//    val config = new java.util.HashMap[String, String]
//    config.put("cluster.name", "neons_cluster")
//
//    val transports = new java.util.ArrayList[InetSocketTransportAddress]
//    transports.add(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300))
//
//    sessionDataStream.addSink(new Elasticsearch5Sink[Session](config, transports, new Elasticsearch5SinkFunction[Session] {
//      def createIndexRequest(element: Session): IndexRequest = {
//
//
//        val json: util.HashMap[String, Object] = new util.HashMap[String, Object]()
//        val sessionEvents = new util.ArrayList[util.HashMap[String, String]]()
//        val jsonSessionEvent = new util.HashMap[String, String]()
//        json.put("visitorId", element.visitorId)
//        json.put("sessionStartedAt", element.sessionStartedAt.toString)
//        json.put("sessionEndedAt", element.sessionEndedAt.toString)
//        json.put("numberOfEvents", element.numberOfEvents.toString)
//        json.put("colorDepth", element.colorDepth)
//        json.put("documentEncoding", element.documentEncoding)
//        json.put("eventType", element.eventType)
//        json.put("language", element.language)
//        json.put("method", element.method)
//        json.put("screenResolution", element.screenResolution)
//        json.put("screenViewport", element.screenViewport)
//        json.put("trackerId", element.trackerId)
//        json.put("firstEventStartedAt", element.firstEventStartedAt.toString)
//        json.put("lastEventEndedAt", element.lastEventEndedAt.toString)
//
//
//        element.sessionEvents.foreach(f => {
//          jsonSessionEvent.put("documentLocation", f.documentLocation)
//          jsonSessionEvent.put("documentLocation", f.documentTitle)
//          sessionEvents.add(jsonSessionEvent)
//        })
//
//        json.put("sessionEvents", sessionEvents)
//
//        Requests.indexRequest()
//          .index("neons")
//          .`type`("sessions")
//          .id(element.sessionStartedAt.toString + "_" + element.visitorId)
//          .source(json)
//      }
//
//      override def process(element: Session, client: Client): Unit = {
//        client.index(createIndexRequest(element))
//      }
//    }))
    //



    env.execute()
  }


}
