package io.neons.streamer.domain.localization

trait LocalizationProvider {
  def localize(ipAddress: String): Localization
}
