package io.neons.streamer.application.flink

import java.lang

import org.apache.flink.api.common.typeinfo.{BasicTypeInfo, TypeInformation}
import org.apache.flink.api.java.tuple
import org.apache.flink.api.java.typeutils.TypeExtractor
import org.apache.flink.streaming.api.datastream
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction
import org.apache.flink.table.sinks.{RetractStreamTableSink, TableSink}
import org.apache.flink.types.Row

/**
  * Created by michal on 14.10.17.
  */
class PrintlnSink extends RetractStreamTableSink[Row] {
  override def getRecordType: TypeInformation[Row] = {
    TypeExtractor.getForClass(Predef.classOf[Row])
  }

  override def emitDataStream(dataStream: datastream.DataStream[tuple.Tuple2[lang.Boolean, Row]]): Unit = {
    dataStream.addSink(new PrintSinkFunction[tuple.Tuple2[lang.Boolean, Row]]())
  }

  override def getFieldNames: Array[String] = {
    Array("date", "count")
  }

  override def getFieldTypes: Array[TypeInformation[_]] = {
    Array(
      BasicTypeInfo.STRING_TYPE_INFO,
      BasicTypeInfo.STRING_TYPE_INFO
    )
  }

  override def configure(
                          fieldNames: Array[String],
                          fieldTypes: Array[TypeInformation[_]]): TableSink[tuple.Tuple2[lang.Boolean, Row]] = {
    new PrintlnSink
  }
}
