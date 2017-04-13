package io.neons.common.event.builder

import com.netaporter.uri.Uri._
import io.neons.common.event.event.Event
import io.neons.common.log.{Log, LogUriParameters}

class UriPartsBuilder extends PartsBuilder {
  override def buildFrom(log: Log, event: Event): Unit = {
    try {
      val uri = parse(log.uri)

      uri.query.params.foreach { case (i, v) =>
        i match {
          case LogUriParameters.eventType => event.eventType = v.getOrElse(Event.pageViewEventType)
          case LogUriParameters.language => event.language = v.getOrElse(event.language)
          case LogUriParameters.tracker => event.trackerId = v.getOrElse(event.trackerId)
          case LogUriParameters.documentEncoding => event.documentEncoding = v.getOrElse(event.documentEncoding)
          case LogUriParameters.screenResolution => event.screenResolution = v.getOrElse(event.screenResolution)
          case LogUriParameters.screenViewport => event.screenViewport = v.getOrElse(event.screenViewport)
          case LogUriParameters.colorDepth => event.colorDepth = v.getOrElse(event.colorDepth)
          case LogUriParameters.documentLocation => event.documentLocation = v.getOrElse(event.documentLocation)
          case LogUriParameters.documentTitle => event.documentTitle = v.getOrElse(event.documentTitle)
          case LogUriParameters.visitorId => event.visitorId = v.getOrElse(event.visitorId)
          case _ => event
        }
      }
    } catch {
      case _: Throwable => println("error with getting query parameters")
    }
  }
}
