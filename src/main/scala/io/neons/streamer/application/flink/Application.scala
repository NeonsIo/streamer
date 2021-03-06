package io.neons.streamer.application.flink

import com.datastax.driver.core.Cluster
import io.neons.streamer.application.flink.builder.EventBuilder
import io.neons.streamer.application.flink.collector.SessionCollector
import io.neons.streamer.application.flink.environment.ExecutionEnvironment
import io.neons.streamer.application.flink.source.KafkaConsumer
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.ContinuousEventTimeTrigger
import com.typesafe.config.ConfigFactory
import io.neons.streamer.application.flink.sink.ElasticsearchSinkImpl
import io.neons.streamer.domain.session.Session
import org.apache.flink.streaming.connectors.cassandra.{CassandraSink, ClusterBuilder}
import org.apache.flink.streaming.connectors.cassandra.CassandraSink


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


//    sessionDataStream.addSink(ElasticsearchSinkImpl.runWith(
//      appConfig.getString("streamer.elasticsearch-cluster-name"),
//      appConfig.getString("streamer.elasticsearch-bulk-flush-max-actions"),
//      appConfig.getObjectList("streamer.elasticsearch-cluster").asScala.toList
//    ))

      CassandraSink
      .addSink(sessionDataStream.javaStream)
//      .setQuery("INSERT INTO sessions (userVisitorId, sessionStartedAt) values (?, ?);")
      .setClusterBuilder(new ClusterBuilder {
        override def buildCluster(builder: Cluster.Builder): Cluster = {
          builder.addContactPoint("10.0.0.60").build()
          builder.addContactPoint("10.0.0.61").build()
        }
      })
      .build()



    sessionDataStream.print()
    env.execute()
  }
}
