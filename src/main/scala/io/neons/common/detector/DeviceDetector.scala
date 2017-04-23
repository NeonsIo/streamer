package io.neons.common.detector

trait DeviceDetector {
  def detect(userAgent: String): Device
}
