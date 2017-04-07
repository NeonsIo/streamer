package io.neons.streamer.event.builder

import io.neons.common.event.builder.UriPartsBuilder
import io.neons.common.event.builder.EventBuilder
import io.neons.common.log.Log
import io.neons.common.event.event.Event

object EventBuilder {
  def build(log: Log): Event = {
    val builder = new EventBuilder(Seq(
      new UriPartsBuilder()
    ))

    builder.buildFrom(log)
  }
}
