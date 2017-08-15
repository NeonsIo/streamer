package io.neons.streamer.domain.event.builder

import io.neons.streamer.domain.detector.DeviceDetector
import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.{HeaderBag, Log}

class DevicePartsBuilder(deviceDetector: DeviceDetector) extends PartsBuilder {
  override def buildFrom(log: Log, event: Event): Unit = {
    val device = deviceDetector.detect(
      log.headers
        .find(p => p.name.toLowerCase == "user-agent")
        .getOrElse(HeaderBag("User-Agent", ""))
        .value
    )

    event.deviceBrowserName = device.browserName
    event.deviceBrowserVersion = device.browserVersion
    event.deviceType = device.deviceType
    event.devicePlatformName = device.platformName
    event.devicePlatformVersion = device.platformVersion
    event.deviceIsMobile = device.isMobile
  }
}
