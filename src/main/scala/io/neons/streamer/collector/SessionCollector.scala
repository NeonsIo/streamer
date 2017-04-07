package io.neons.streamer.collector

import io.neons.common.event.event.Event
import io.neons.common.session.{Session, SessionEvent}
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import scala.collection.mutable.ListBuffer

class SessionCollector extends WindowFunction[Event, Session, String, TimeWindow] {
  override def apply(key: String, window: TimeWindow, input: Iterable[Event], out: Collector[Session]): Unit = {

    val sessionEvents = ListBuffer[SessionEvent]()

    for (in <- input) {
      sessionEvents += SessionEvent(
        in.documentLocation,
        in.documentTitle
      )
    }

    out.collect(Session(
      window.getStart,
      window.getEnd,
      input.last.visitorId,
      input.last.trackerId,
      input.head.eventDate,
      input.last.eventDate,
      input.size,
      input.last.method,
      input.last.eventType,
      input.last.language,
      input.last.documentEncoding,
      input.last.screenResolution,
      input.last.screenViewport,
      input.last.colorDepth,
      sessionEvents
    ))
  }
}

object SessionCollector {
  def collect = new SessionCollector()
}
