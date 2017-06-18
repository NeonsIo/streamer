package io.neons.streamer.domain.event.builder

import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.Log

final class EventBuilder(uriPartsBuilder: Seq[PartsBuilder]) extends Serializable {
  def buildFrom(log: Log): Event = {
    val event = createInitialStateFrom(log)
    uriPartsBuilder.foreach {
      parts => parts.buildFrom(log, event)
    }
    event
  }

  private def createInitialStateFrom(log: Log) = Event(
    requestId = log.requestUuidL,
    requestMethod = log.method,
    requestEventDate = log.serverDate
  )
}
