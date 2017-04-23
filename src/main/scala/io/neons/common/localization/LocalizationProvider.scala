package io.neons.common.localization

trait LocalizationProvider {
  def localize(ipAddress: String): Localization
}
