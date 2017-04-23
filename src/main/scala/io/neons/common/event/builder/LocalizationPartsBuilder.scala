package io.neons.common.event.builder

import io.neons.common.event.event.Event
import io.neons.common.localization.LocalizationProvider
import io.neons.common.log.Log

class LocalizationPartsBuilder(localizationProvider: LocalizationProvider) extends PartsBuilder {
  override def buildFrom(log: Log, event: Event): Unit = {
    val localization = this.localizationProvider.localize(log.clientIp)

    event.localizationCityName = localization.cityName
    event.localizationCountryIsoCode = localization.countryIsoCode
    event.localizationCountryName = localization.countryName
    event.localizationSubdivisionIsoCode = localization.subdivisionIsoCode
    event.localizationSubdivisionName = localization.subdivisionName
    event.localizationPostalCode = localization.postalCode
    event.localizationLat = localization.lat
    event.localizationLon = localization.lon
  }
}
