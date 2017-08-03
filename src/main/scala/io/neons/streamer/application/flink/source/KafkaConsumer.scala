package io.neons.streamer.application.flink.source

import java.util.Properties

import io.neons.streamer.domain.log.Log
import io.neons.streamer.application.flink.generator.BoundedOutOfOrdernessGenerator
import io.neons.streamer.application.flink.schema.LogSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010

object KafkaConsumer {
  def create(kafkaServer: String, kafkaGroupId: String, kafkaTopic: String, maxTimeLag: Long) = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", kafkaServer)
    properties.setProperty("group.id", kafkaGroupId)

    val kafkaConsumer = new FlinkKafkaConsumer010[Log](kafkaTopic, new LogSchema(), properties)

    kafkaConsumer.assignTimestampsAndWatermarks(BoundedOutOfOrdernessGenerator getWith maxTimeLag)
    kafkaConsumer
  }
}
