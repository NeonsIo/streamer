package io.neons.streamer

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Cluster.Builder
import io.neons.common.event.metric.SessionPerDay
import io.neons.common.session.Session
import io.neons.streamer.collector.SessionCollector
import io.neons.streamer.environment.ExecutionEnvironment
import io.neons.streamer.event.builder.EventBuilder
import io.neons.streamer.source.KafkaConsumer
import org.apache.flink.api.common.functions.{MapFunction, RichFlatMapFunction}
import org.apache.flink.api.common.state.{FoldingState, FoldingStateDescriptor}
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}
import org.apache.flink.runtime.state.GenericFoldingState
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.functions.ProcessFunction.{Context, OnTimerContext}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.ContinuousEventTimeTrigger
import org.apache.flink.streaming.connectors.cassandra.{CassandraSink, ClusterBuilder}
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.table.api.scala.table2TableConversions
import org.apache.flink.util.Collector
import org.apache.flink.configuration.Configuration

object Application {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = ExecutionEnvironment.create

    val sessionDataStream = env
      .addSource(KafkaConsumer.create)
      .map(log => EventBuilder build log)
      .keyBy(_.trackerId)
      .keyBy(_.visitorId)
      .window(EventTimeSessionWindows.withGap(Time.minutes(30)))
      .trigger(ContinuousEventTimeTrigger of Time.seconds(30))
      .apply(SessionCollector.collect)

    sessionDataStream.print()


//
//    val sessionPerDayStream = sessionDataStream
//      .map(session => {
//        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
//        cal.setTimeInMillis(session.firstEventStartedAt)
//        val simpleDateFormat = new SimpleDateFormat("Y-MM-dd")
//
//        (simpleDateFormat.format(cal.getTime), session.sessionStartedAt + "." + session.visitorId, 1)
//      })
//
//
//    sessionPerDayStream
//        .keyBy(0)
//        .flatMapWithState((in, state: Option[scala.collection.mutable.Map[String, Int]]) => {
//
//          state match {
//            case Some(current) => {
//              var map = current
//              map += current.get(in._2).map(x => in._2 -> (x + in._3)).getOrElse(in._2 -> in._3)
//              (List(Tuple2(in._1, state.get.size)), Some(map))
//            }
//            case None => {
//              val map = scala.collection.mutable.Map[String, Int]()
//              map(in._2) = in._3
//              (List(Tuple2(in._1, 1)), Some(map))
//            }
//          }
//        })
//      .print()


    env.execute()
  }


}
