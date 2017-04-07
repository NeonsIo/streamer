package io.neons.common.event.event

import java.util.UUID

case class Event(
                  requestId: String, //ok
                  method: String, //ok
                  eventDate: Long, //ok
                  var visitorId: String = UUID.randomUUID().toString, //ok
                  var eventType: String = Event.pageViewEventType, //ok
                  var language: String = "", //ok
                  var trackerId: String = "", //ok
                  var documentEncoding: String = "",//ok
                  var screenResolution: String = "",
                  var screenViewport: String = "",
                  var colorDepth: String = "",
                  var documentLocation: String = "",
                  var documentTitle: String = ""
                )

object Event {
  val pageViewEventType = "pageView"
}
