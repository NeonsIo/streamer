package io.neons.streamer.domain.event.event

import java.util.UUID

case class Event(var requestId: String,
                 var requestMethod: String,
                 var requestEventDate: Long,
                 var thirdPartyVisitorId: String = UUID.randomUUID().toString,
                 var requestEventType: String = Event.pageViewEventType,
                 var firstPartyVisitorId: String = UUID.randomUUID().toString,
                 var userLanguage: String = "",
                 var userTrackerId: String = "",
                 var documentLocation: String = "",
                 var documentTitle: String = "",
                 var documentEncoding: String = "",
                 var deviceBrowserName: String = "",
                 var deviceBrowserVersion: String = "",
                 var deviceScreenResolution: String = "",
                 var deviceScreenViewport: String = "",
                 var deviceColorDepth: String = "",
                 var devicePlatformName: String = "",
                 var devicePlatformVersion: String = "",
                 var deviceType: String = "",
                 var deviceIsMobile: String = "",
                 var localizationCountryIsoCode: String = "",
                 var localizationCountryName: String = "",
                 var localizationSubdivisionIsoCode: String = "",
                 var localizationSubdivisionName: String = "",
                 var localizationCityName: String = "",
                 var localizationPostalCode: String = "",
                 var localizationLat: Option[Double] = None,
                 var localizationLon: Option[Double] = None
                )

object Event {
  val pageViewEventType = "pageView"
}
