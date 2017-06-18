package io.neons.streamer.domain.localization

case class Localization(countryIsoCode: String,
                        countryName: String,
                        subdivisionIsoCode: String,
                        subdivisionName: String,
                        cityName: String,
                        postalCode: String,
                        lat: Option[Double],
                        lon: Option[Double])
