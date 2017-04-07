package io.neons.streamer

import io.neons.streamer.collector.SessionCollector
import io.neons.streamer.environment.ExecutionEnvironment
import io.neons.streamer.event.builder.EventBuilder
import io.neons.streamer.source.KafkaConsumer
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.ContinuousEventTimeTrigger

object Application {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = ExecutionEnvironment.create

    env
      .addSource(KafkaConsumer.create)
      .map(log => EventBuilder build log)
      .keyBy(_.trackerId)
      .keyBy(_.visitorId)
      .window(EventTimeSessionWindows withGap Time.minutes(30))
      .trigger(ContinuousEventTimeTrigger of Time.seconds(30))
      .apply(SessionCollector.collect)
      .print()

    env.execute()
  }
}
