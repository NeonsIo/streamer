package io.neons.common.event.builder

import io.neons.common.event.event.Event
import io.neons.common.log.Log

trait PartsBuilder {
  def buildFrom(log: Log, event: Event)
}
