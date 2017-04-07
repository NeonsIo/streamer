package io.neons.common.event.builder

import io.neons.common.event.event.Event
import io.neons.common.log.Log

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
    method = log.method,
    eventDate = log.serverDate
  )
}
