package io.neons.streamer.schema

import io.neons.common.log.Log
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.typeutils.TypeExtractor
import org.apache.flink.streaming.util.serialization.{DeserializationSchema, SerializationSchema}
import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}

final class LogSchema extends DeserializationSchema[Log] with SerializationSchema[Log] {
  override def deserialize(message: Array[Byte]): Log = {
    implicit val formats = Serialization.formats(NoTypeHints)
    read[Log](new String(message))
  }

  override def isEndOfStream(nextElement: Log): Boolean = false

  override def serialize(element: Log): Array[Byte] = {
    implicit val formats = Serialization.formats(NoTypeHints)
    write(element).getBytes
  }

  override def getProducedType: TypeInformation[Log] = TypeExtractor.getForClass(Predef.classOf[Log])
}
