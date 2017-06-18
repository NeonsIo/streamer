package io.neons.streamer.application.flink.source

import java.util.Properties

import io.neons.streamer.domain.log.Log
import io.neons.streamer.application.flink.generator.BoundedOutOfOrdernessGenerator
import io.neons.streamer.application.flink.schema.LogSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010

object KafkaConsumer {
  def create = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "test")

    val kafkaConsumer = new FlinkKafkaConsumer010[Log]("neons_logs", new LogSchema(), properties)

    kafkaConsumer.assignTimestampsAndWatermarks(BoundedOutOfOrdernessGenerator getWith 5000L)
    kafkaConsumer
  }
}
