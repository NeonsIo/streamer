package io.neons.streamer.generator

import io.neons.common.log.Log
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

class BoundedOutOfOrdernessGenerator(maxTimeLag: Long) extends AssignerWithPeriodicWatermarks[Log] {
  var currentMaxTimestamp: Long = _

  override def extractTimestamp(element: Log, previousElementTimestamp: Long): Long = {
    val timestamp = element.serverDate
    currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp)
    timestamp
  }

  override def getCurrentWatermark: Watermark = {
    new Watermark(System.currentTimeMillis() - maxTimeLag)
  }
}

object BoundedOutOfOrdernessGenerator {
  def getWith(maxTimeLag: Long) = new BoundedOutOfOrdernessGenerator(maxTimeLag)
}
