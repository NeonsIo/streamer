package io.neons.streamer.domain.detector

trait DeviceDetector {
  def detect(userAgent: String): Device
}
