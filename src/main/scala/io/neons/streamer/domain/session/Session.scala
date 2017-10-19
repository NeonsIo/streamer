package io.neons.streamer.domain.session

import java.sql.Timestamp

case class SessionEvent(requestId: String,
                        requestMethod: String,
                        requestEventDate: Timestamp,
                        requestEventType: String,
                        documentLocation: String,
                        documentTitle: String,
                        documentEncoding: String)

case class Session(
                   sessionId: String,
                   sessionStartedAt: Timestamp,
                   sessionEndedAt: Timestamp,
                   lastEventEndedAt: Timestamp,
                   firstEventStartedAt: Timestamp,
                   numberOfEvents: Int,
                   requestVisitorId: String,
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
                   localizationLat: Option[Double],
                   localizationLon: Option[Double],
                   sessionEvents: Seq[SessionEvent])