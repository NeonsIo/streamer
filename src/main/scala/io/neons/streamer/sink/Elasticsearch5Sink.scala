package io.neons.streamer.sink

import java.net.{InetAddress, InetSocketAddress}

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient

class Elasticsearch5Sink[IN](
                              clientConfig: java.util.Map[String, String],
                              transports: java.util.List[InetSocketTransportAddress],
                              elasticsearch5Function: Elasticsearch5SinkFunction[IN]) extends RichSinkFunction[IN] {

  var client: Client = _

  override def invoke(value: IN): Unit = {
    elasticsearch5Function.process(value, this.client)
  }

  override def open(parameters: Configuration) = {
    this.client = this.createClient
  }

  def createClient: Client = {
    val settings = Settings.builder().put(this.clientConfig).build()
    val transportClient = new PreBuiltTransportClient(settings)
    transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300))

    if (transportClient.listedNodes().isEmpty) {
      throw new Exception("Not connected")
    }
//    println(this.transports.size())
//    for (transport <- this.transports) transportClient.addTransportAddress(transport)
    transportClient
  }
}
