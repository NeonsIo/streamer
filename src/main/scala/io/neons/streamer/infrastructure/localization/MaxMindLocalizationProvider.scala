package io.neons.streamer.infrastructure.localization

import java.net.InetAddress
import com.maxmind.geoip2.DatabaseReader
import io.neons.streamer.domain.localization.{Localization, LocalizationProvider}

class MaxMindLocalizationProvider(databaseReader: DatabaseReader) extends LocalizationProvider {
  override def localize(ip: String): Localization = {
    try {
      val ipAddress = InetAddress.getByName(ip)
      val response = this.databaseReader.city(ipAddress)
      val country = response.getCountry
      val subdivision = response.getMostSpecificSubdivision
      val city = response.getCity
      val postal = response.getPostal
      val location = response.getLocation

      Localization(
        country.getIsoCode,
        country.getName,
        subdivision.getIsoCode,
        subdivision.getName,
        city.getName,
        postal.getCode,
        Some(location.getLatitude),
        Some(location.getLongitude)
      )
    } catch {
      case _: Throwable =>
        Localization(
          "Unknown",
          "Unknown",
          "Unknown",
          "Unknown",
          "Unknown",
          "Unknown",
          None,
          None
        )
    }
  }
}

object MaxMindLocalizationProvider {
  def get = new MaxMindLocalizationProvider(new DatabaseReader.Builder(
    getClass.getClassLoader.getResourceAsStream("GeoLite2-City.mmdb")
  ).build)
}
