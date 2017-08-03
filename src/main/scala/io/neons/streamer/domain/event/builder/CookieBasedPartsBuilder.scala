package io.neons.streamer.domain.event.builder

import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.{HeaderBag, Log}

class CookieBasedPartsBuilder(cookieName: String) extends PartsBuilder {
  override def buildFrom(log: Log, event: Event): Unit = {
    val neonsVisitorId = log.cookies
      .find(p => p.name.toLowerCase == cookieName)
      .getOrElse(HeaderBag(cookieName, ""))
      .value

    event.thirdPartyVisitorId = neonsVisitorId
  }
}
