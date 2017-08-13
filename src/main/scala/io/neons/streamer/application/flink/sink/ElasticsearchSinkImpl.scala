package io.neons.streamer.application.flink.sink

import java.net.{InetAddress, InetSocketAddress}

import com.typesafe.config.ConfigObject
import io.neons.streamer.domain.session.Session
import org.apache.flink.streaming.connectors.elasticsearch5.ElasticsearchSink

object ElasticsearchSinkImpl {
  def runWith(clusterName: String, maxActions: String, clusterList: List[ConfigObject]) = {
    val config = new java.util.HashMap[String, String]
    config.put("cluster.name", clusterName)
    config.put("bulk.flush.max.actions", maxActions)

    val transportAddresses = new java.util.ArrayList[InetSocketAddress]

    clusterList
      .filter(_.size() != 0)
      .foreach(u => {
        transportAddresses.add(
          new InetSocketAddress(
            InetAddress.getByName(u.get("host").unwrapped().toString),
            Integer.valueOf(u.get("port").unwrapped().toString)
          )
        )
      })

    new ElasticsearchSink[Session](config, transportAddresses, new ElasticsearchSinkFunctionImpl())
  }
}
