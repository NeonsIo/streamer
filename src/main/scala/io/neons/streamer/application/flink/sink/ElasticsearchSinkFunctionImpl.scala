package io.neons.streamer.application.flink.sink

import java.util

import io.neons.streamer.domain.session.Session
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests

class ElasticsearchSinkFunctionImpl extends ElasticsearchSinkFunction[Session] {
  def createIndexRequest(session: Session): IndexRequest = {
    val sessionMap = new java.util.HashMap[String, Any]
    sessionMap.put("sessionStartedAt", session.sessionStartedAt)
    sessionMap.put("sessionEndedAt", session.sessionEndedAt)
    sessionMap.put("lastEventEndedAt", session.lastEventEndedAt)
    sessionMap.put("firstEventStartedAt", session.firstEventStartedAt)
    sessionMap.put("numberOfEvents", session.numberOfEvents)
    sessionMap.put("requestVisitorId", session.requestVisitorId)
    sessionMap.put("userTrackerId", session.userTrackerId)
    sessionMap.put("userVisitorId", session.userVisitorId)
    sessionMap.put("userLanguage", session.userLanguage)
    sessionMap.put("deviceScreenResolution", session.deviceScreenResolution)
    sessionMap.put("deviceScreenViewport", session.deviceScreenViewport)
    sessionMap.put("deviceColorDepth", session.deviceColorDepth)
    sessionMap.put("deviceBrowserName", session.deviceBrowserName)
    sessionMap.put("deviceBrowserVersion", session.deviceBrowserVersion)
    sessionMap.put("devicePlatformName", session.devicePlatformName)
    sessionMap.put("devicePlatformVersion", session.devicePlatformVersion)
    sessionMap.put("deviceType", session.deviceType)
    sessionMap.put("deviceIsMobile", session.deviceIsMobile.toBoolean)
    sessionMap.put("localizationCountryIsoCode", session.localizationCountryIsoCode)
    sessionMap.put("localizationCountryName", session.localizationCountryName)
    sessionMap.put("localizationSubdivisionIsoCode", session.localizationSubdivisionIsoCode)
    sessionMap.put("localizationSubdivisionName", session.localizationSubdivisionName)
    sessionMap.put("localizationCityName", session.localizationCityName)
    sessionMap.put("localizationPostalCode", session.localizationPostalCode)

    if (session.localizationLat.isDefined && session.localizationLon.isDefined) {
      sessionMap.put("localization", session.localizationLat.get + "," +session.localizationLon.get)
    }

    val sessionEvent = new java.util.HashMap[String, Any]
    val sessionEvents = new util.ArrayList[java.util.HashMap[String, Any]]()
    session.sessionEvents.foreach(se => {
      sessionEvent.put("requestId", se.requestId)
      sessionEvent.put("requestMethod", se.requestMethod)
      sessionEvent.put("requestEventDate", se.requestEventDate)
      sessionEvent.put("requestEventType", se.requestEventType)
      sessionEvent.put("documentLocation", se.documentLocation)
      sessionEvent.put("documentTitle", se.documentTitle)
      sessionEvent.put("documentEncoding", se.documentEncoding)
      sessionEvents.add(sessionEvent)
    })

    sessionMap.put("sessionEvents", sessionEvents)

    Requests.indexRequest()
      .index("neons")
      .`type`("sessions")
      .id(session.sessionStartedAt + "." + session.userVisitorId)
      .source(sessionMap)
  }

  override def process(t: Session, runtimeContext: RuntimeContext, requestIndexer: RequestIndexer): Unit = {
    requestIndexer.add(createIndexRequest(t))
  }
}
