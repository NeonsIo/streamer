package io.neons.streamer.sink

import org.elasticsearch.client.Client

trait Elasticsearch5SinkFunction[IN] extends Serializable {
  def process(value: IN, client: Client)
}
