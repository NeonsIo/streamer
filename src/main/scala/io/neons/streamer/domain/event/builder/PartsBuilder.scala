package io.neons.streamer.domain.event.builder

import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.Log

trait PartsBuilder {
  def buildFrom(log: Log, event: Event)
}
