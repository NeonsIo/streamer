package io.neons.common.session

case class SessionEvent(requestId: String,
                        requestMethod: String,
                        requestEventDate: Long,
                        requestEventType: String,
                        documentLocation: String,
                        documentTitle: String,
                        documentEncoding: String)

case class Session(sessionStartedAt: Long,
                   sessionEndedAt: Long,
                   lastEventEndedAt: Long,
                   firstEventStartedAt: Long,
                   numberOfEvents: Int,
                   requestTrackerId: String,
                   userTrackerId: String,
                   userVisitorId: String,
                   userLanguage: String,
                   deviceScreenResolution: String,
                   deviceScreenViewport: String,
                   deviceColorDepth: String,
                   deviceBrowserName: String,
                   deviceBrowserVersion: String,
                   devicePlatformName: String,
                   devicePlatformVersion: String,
                   deviceType: String,
                   deviceIsMobile: String,
                   localizationCountryIsoCode: String,
                   localizationCountryName: String,
                   localizationSubdivisionIsoCode: String,
                   localizationSubdivisionName: String,
                   localizationCityName: String,
                   localizationPostalCode: String,
                   localizationLat: Double,
                   localizationLon: Double,
                   sessionEvents: Seq[SessionEvent])