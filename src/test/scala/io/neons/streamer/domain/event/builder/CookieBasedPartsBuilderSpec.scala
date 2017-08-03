package io.neons.streamer.domain.event.builder

import io.neons.streamer.domain.event.event.Event
import io.neons.streamer.domain.log.{HeaderBag, Log}
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ListBuffer

class CookieBasedPartsBuilderSpec  extends FlatSpec with Matchers {
  "Cookie based parts builder" should "build request visitor id from cookie log if exists" in {
    val builder = new CookieBasedPartsBuilder("cookie")
    val event = Event(
      "82e216d7-2585-4b53-ae62-f9c788cc9a80",
      "GET",
      System.currentTimeMillis()
    )
    val log = Log(
      "82e216d7-2585-4b53-ae62-f9c788cc9a80",
      "GET",
      "/",
      ListBuffer(),
      ListBuffer(HeaderBag("cookie", "test")),
      "127.0.0.1",
      System.currentTimeMillis()
    )

    builder.buildFrom(log, event)

    event.thirdPartyVisitorId should be("test")
  }

  it should "have empty value if cookie does not exist" in {
    val builder = new CookieBasedPartsBuilder("cookie2")
    val event = Event(
      "82e216d7-2585-4b53-ae62-f9c788cc9a80",
      "GET",
      System.currentTimeMillis()
    )
    val log = Log(
      "82e216d7-2585-4b53-ae62-f9c788cc9a80",
      "GET",
      "/",
      ListBuffer(),
      ListBuffer(HeaderBag("cookie", "test")),
      "127.0.0.1",
      System.currentTimeMillis()
    )

    builder.buildFrom(log, event)

    event.thirdPartyVisitorId should be("")
  }
}
