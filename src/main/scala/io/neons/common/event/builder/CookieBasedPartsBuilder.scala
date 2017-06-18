package io.neons.common.event.builder

import io.neons.common.event.event.Event
import io.neons.common.log.{HeaderBag, Log}

class CookieBasedPartsBuilder extends PartsBuilder {
  override def buildFrom(log: Log, event: Event): Unit = {
    val neonsVisitorId = log.cookies
      .find(p => p.name.toLowerCase == "nid")
      .getOrElse(HeaderBag("nid", ""))
      .value

    event.requestVisitorId = neonsVisitorId
  }
}
