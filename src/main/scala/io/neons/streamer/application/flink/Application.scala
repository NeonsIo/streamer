package io.neons.streamer.application.flink

import java.net.{InetAddress, InetSocketAddress}

import io.neons.streamer.application.flink.builder.EventBuilder
import io.neons.streamer.application.flink.collector.SessionCollector
import io.neons.streamer.application.flink.environment.ExecutionEnvironment
import io.neons.streamer.application.flink.source.KafkaConsumer
import io.neons.streamer.domain.session.Session
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.ContinuousEventTimeTrigger
import org.apache.flink.streaming.connectors.elasticsearch5.ElasticsearchSink
import com.typesafe.config.ConfigFactory
import io.neons.streamer.application.flink.sink.ElasticsearchSinkFunctionImpl

import scala.collection.JavaConverters._

object Application {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = ExecutionEnvironment.create
    val appConfig = ConfigFactory.load()

    val sessionDataStream = env
      .addSource(KafkaConsumer.create(
        appConfig.getString("streamer.kafka-server"),
        appConfig.getString("streamer.kafka-group-id"),
        appConfig.getString("streamer.kafka-topic"),
        appConfig.getLong("streamer.max-time-lag")
      ))
      .map(log => EventBuilder.build(log, appConfig.getString("streamer.cookie-name")))
      .keyBy(_.userTrackerId)
      .keyBy(_.firstPartyVisitorId)
      .window(EventTimeSessionWindows withGap Time.seconds(appConfig.getLong("streamer.session-window")))
      .trigger(ContinuousEventTimeTrigger of Time.seconds(appConfig.getLong("streamer.session-window-trigger")))
      .apply(SessionCollector.collect)


    val config = new java.util.HashMap[String, String]
    config.put("cluster.name", appConfig.getString("streamer.elasticsearch-cluster-name"))
    config.put("bulk.flush.max.actions", appConfig.getString("streamer.elasticsearch-bulk-flush-max-actions"))

    val transportAddresses = new java.util.ArrayList[InetSocketAddress]

    appConfig.getObjectList("streamer.elasticsearch-cluster").asScala.toList.foreach(u => {
      transportAddresses.add(
        new InetSocketAddress(
          InetAddress.getByName(u.get("host").unwrapped().toString),
          u.get("port").unwrapped().asInstanceOf[Int]
        )
      )
    })



    sessionDataStream.addSink(new ElasticsearchSink[Session](config, transportAddresses, new ElasticsearchSinkFunctionImpl()))
    sessionDataStream.print()


    env.execute()
  }
}
