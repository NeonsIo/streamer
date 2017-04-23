package io.neons.streamer.event.builder

import io.neons.common.event.builder.{DevicePartsBuilder, EventBuilder, LocalizationPartsBuilder, UriPartsBuilder}
import io.neons.common.log.Log
import io.neons.common.event.event.Event
import io.neons.streamer.detector.FiftyOneDegreesDeviceDetector
import io.neons.streamer.localization.MaxMindLocalizationProvider

object EventBuilder {
  def build(log: Log): Event = {
    val builder = new EventBuilder(Seq(
      new UriPartsBuilder(),
      new DevicePartsBuilder(FiftyOneDegreesDeviceDetector.get),
      new LocalizationPartsBuilder(MaxMindLocalizationProvider.get)
    ))

    builder.buildFrom(log)
  }
}
