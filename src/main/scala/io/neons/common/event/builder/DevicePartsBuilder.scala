package io.neons.common.event.builder

import io.neons.common.detector.DeviceDetector
import io.neons.common.event.event.Event
import io.neons.common.log.{HeaderBag, Log}

class DevicePartsBuilder(deviceDetector: DeviceDetector) extends PartsBuilder {
  override def buildFrom(log: Log, event: Event): Unit = {
    val userAgent = log.headers
      .find(p => p.name.toLowerCase == "user-agent")
      .getOrElse(HeaderBag("User-Agent", ""))
      .value
    val device = deviceDetector.detect(userAgent)

    event.deviceBrowserName = device.browserName
    event.deviceBrowserVersion = device.browserVersion
    event.deviceType = device.deviceType
    event.devicePlatformName = device.platformName
    event.devicePlatformVersion = device.platformVersion
    event.deviceIsMobile = device.isMobile
  }
}
