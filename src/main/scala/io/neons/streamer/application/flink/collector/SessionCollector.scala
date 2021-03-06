package io.neons.streamer.application.flink.collector

import java.sql.Timestamp

import io.neons.streamer.application.flink.SessionPojo
import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.session.{Session, SessionEvent}
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

import scala.collection.mutable.ListBuffer

class SessionCollector extends WindowFunction[Event, SessionPojo, String, TimeWindow] {
  override def apply(key: String, window: TimeWindow, input: Iterable[Event], out: Collector[SessionPojo]): Unit = {
    val sessionEvents = ListBuffer[SessionEvent]()

    for (in <- input) {
      sessionEvents += SessionEvent(
        in.requestId,
        in.requestMethod,
        new Timestamp(in.requestEventDate),
        in.requestEventType,
        in.documentLocation,
        in.documentTitle,
        in.documentEncoding
      )
    }

    out.collect(new SessionPojo(Session(
      window.getStart + input.last.firstPartyVisitorId,
      new Timestamp(window.getStart),
      new Timestamp(window.getEnd),
      new Timestamp(input.head.requestEventDate),
      new Timestamp(input.last.requestEventDate),
      input.size,
      input.last.thirdPartyVisitorId,
      input.last.userTrackerId,
      input.last.firstPartyVisitorId,
      input.last.userLanguage,
      input.last.deviceScreenResolution,
      input.last.deviceScreenViewport,
      input.last.deviceColorDepth,
      input.last.deviceBrowserName,
      input.last.deviceBrowserVersion,
      input.last.devicePlatformName,
      input.last.devicePlatformVersion,
      input.last.deviceType,
      input.last.deviceIsMobile,
      input.last.localizationCountryIsoCode,
      input.last.localizationCountryName, 
      input.last.localizationSubdivisionIsoCode,
      input.last.localizationSubdivisionName,
      input.last.localizationCityName,
      input.last.localizationPostalCode,
      input.last.localizationLat,
      input.last.localizationLon,
      sessionEvents
    )))
  }
}

object SessionCollector {
  def collect = new SessionCollector()
}
