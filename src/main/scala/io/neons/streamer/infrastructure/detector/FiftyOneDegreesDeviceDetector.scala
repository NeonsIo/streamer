package io.neons.streamer.infrastructure.detector

import fiftyone.mobile.detection.Provider
import fiftyone.mobile.detection.factories.StreamFactory
import io.neons.streamer.domain.detector.{Device, DeviceDetector}
import org.apache.commons.io.IOUtils

class FiftyOneDegreesDeviceDetector(provider: Provider) extends DeviceDetector {
  override def detect(userAgent: String): Device = {
    val deviceMatch = provider.`match`(userAgent)
    val list = List(
      "BrowserName",
      "BrowserVersion",
      "PlatformName",
      "PlatformVersion",
      "DeviceType",
      "IsMobile"
    ).map(p =>
      deviceMatch.getValues(p) match {
        case null => "Unknown"
        case _ => deviceMatch.getValues(p).toString
      }
    )

    Device(list.head, list(1), list(2), list(3), list(4), list.last)
  }
}

object FiftyOneDegreesDeviceDetector {
  val embeddedProvider = new Provider(
    StreamFactory.create(IOUtils.toByteArray(getClass.getClassLoader.getResourceAsStream("51Degrees.dat")))
  )

  def get = new FiftyOneDegreesDeviceDetector(embeddedProvider)
}
