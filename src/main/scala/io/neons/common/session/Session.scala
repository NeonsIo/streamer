package io.neons.common.session

case class SessionEvent(documentLocation: String, documentTitle: String)

case class Session(sessionStartedAt: Long,
                   sessionEndedAt: Long,
                   visitorId: String,
                   trackerId: String,
                   firstEventStartedAt: Long,
                   lastEventEndedAt: Long,
                   numberOfEvents: Int,
                   method: String,
                   eventType: String,
                   language: String,
                   documentEncoding: String,
                   screenResolution: String,
                   screenViewport: String,
                   colorDepth: String,
                   sessionEvents: Seq[SessionEvent]
                  )