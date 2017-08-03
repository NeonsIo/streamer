package io.neons.streamer.application.flink.builder

import io.neons.streamer.domain.event.builder._
import io.neons.streamer.domain.log.Log
import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.infrastructure.detector.FiftyOneDegreesDeviceDetector
import io.neons.streamer.infrastructure.localization.MaxMindLocalizationProvider

object EventBuilder {
  def build(log: Log, cookieName: String): Event = {
    val builder = new EventBuilder(Seq(
      new UriPartsBuilder(),
      new CookieBasedPartsBuilder(cookieName),
      new DevicePartsBuilder(FiftyOneDegreesDeviceDetector.get),
      new LocalizationPartsBuilder(MaxMindLocalizationProvider.get)
    ))

    builder.buildFrom(log)
  }
}
