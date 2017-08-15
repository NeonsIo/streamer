package io.neons.streamer.domain.event.builder

import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.Log

final class EventBuilder(partBuilders: Seq[PartsBuilder]) extends Serializable {
  def buildFrom(log: Log): Event = {
    val event = Event(
      requestId = log.requestUuidL,
      requestMethod = log.method,
      requestEventDate = log.serverDate
    )
    partBuilders.foreach {
      parts => parts.buildFrom(log, event)
    }
    event
  }
}
