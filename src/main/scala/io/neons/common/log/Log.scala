package io.neons.common.log

import scala.collection.mutable.ListBuffer

case class HeaderBag(name: String, value: String)

final case class Log(
                requestUuidL: String,
                method: String,
                uri: String,
                headers: ListBuffer[HeaderBag],
                cookies: ListBuffer[HeaderBag],
                clientIp: String,
                serverDate: Long
              )

object LogUriParameters {
  val eventType = "e"
  val language = "l"
  val tracker = "t"
  val documentEncoding = "de"
  val screenResolution = "sr"
  val screenViewport = "sv"
  val colorDepth = "cd"
  val deviceIdentification = "di"
  val documentLocation = "dl"
  val documentTitle = "dt"
  val visitorId = "vid"
  val sessionId = "sid"
  val sessionTime = "st"
}
