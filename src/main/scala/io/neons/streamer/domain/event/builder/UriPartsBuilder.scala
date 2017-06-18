package io.neons.streamer.domain.event.builder

import com.netaporter.uri.Uri._
import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.{Log, LogUriParameters}

class UriPartsBuilder extends PartsBuilder {
  override def buildFrom(log: Log, event: Event): Unit = {
    try {
      val uri = parse(log.uri)

      uri.query.params.foreach { case (i, v) =>
        i match {
          case LogUriParameters.requestEventType => event.requestEventType = v.getOrElse(Event.pageViewEventType)
          case LogUriParameters.userLanguage => event.userLanguage = v.getOrElse(event.userLanguage)
          case LogUriParameters.userTracker => event.userTrackerId = v.getOrElse(event.userTrackerId)
          case LogUriParameters.userVisitorId => event.userVisitorId = v.getOrElse(event.userVisitorId)
          case LogUriParameters.deviceScreenResolution => event.deviceScreenResolution = v.getOrElse(event.deviceScreenResolution)
          case LogUriParameters.deviceScreenViewport => event.deviceScreenViewport = v.getOrElse(event.deviceScreenViewport)
          case LogUriParameters.deviceColorDepth => event.deviceColorDepth = v.getOrElse(event.deviceColorDepth)
          case LogUriParameters.documentEncoding => event.documentEncoding = v.getOrElse(event.documentEncoding)
          case LogUriParameters.documentLocation => event.documentLocation = v.getOrElse(event.documentLocation)
          case LogUriParameters.documentTitle => event.documentTitle = v.getOrElse(event.documentTitle)
          case _ => event
        }
      }
    } catch {
      case _: Throwable => println("error with getting query parameters")
    }
  }
}
