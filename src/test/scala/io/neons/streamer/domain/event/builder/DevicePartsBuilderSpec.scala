package io.neons.streamer.domain.event.builder

import io.neons.streamer.domain.detector.{Device, DeviceDetector}
import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.{HeaderBag, Log}
import org.scalatest.{FlatSpec, Matchers}
import org.mockito.Mockito._

import scala.collection.mutable.ListBuffer
import org.scalatest.mockito.MockitoSugar

class DevicePartsBuilderSpec  extends FlatSpec with Matchers with MockitoSugar {
  "Device parts builder" should "build device" in {

    val deviceDetector = mock[DeviceDetector]
    val userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/59.0.3071.109 Chrome/59.0.3071.109 Safari/537.36"
    when(deviceDetector.detect(userAgent)).thenReturn(Device(
      "Chromium",
      "59.0.3071.109",
      "Ubuntu",
      "Unknown",
      "Desktop",
      "false"
    ))
    val builder = new DevicePartsBuilder(deviceDetector)
    val event = Event(
      "82e216d7-2585-4b53-ae62-f9c788cc9a80",
      "GET",
      System.currentTimeMillis()
    )
    val log = Log(
      "82e216d7-2585-4b53-ae62-f9c788cc9a80",
      "GET",
      "/",
      ListBuffer(HeaderBag("User-Agent", userAgent)),
      ListBuffer(HeaderBag("cookie", "test")),
      "127.0.0.1",
      System.currentTimeMillis()
    )

    builder.buildFrom(log, event)

    event.deviceBrowserName should be("Chromium")
    event.deviceBrowserVersion should be("59.0.3071.109")
    event.deviceType should be("Desktop")
    event.devicePlatformName should be("Ubuntu")
    event.devicePlatformVersion should be("Unknown")
    event.deviceIsMobile should be("false")
  }
}
