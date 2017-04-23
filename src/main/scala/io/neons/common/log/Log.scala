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
  val requestEventType = "e"
  val userVisitorId = "vid"
  val userLanguage = "l"
  val userTracker = "t"
  val documentEncoding = "de"
  val deviceScreenResolution = "sr"
  val deviceScreenViewport = "sv"
  val deviceColorDepth = "cd"
  val deviceIdentification = "di"
  val documentLocation = "dl"
  val documentTitle = "dt"
}
