package mappers

import org.joda.time.Duration

import models.{GroupedLapInput, LapInput}

trait BaseMapper {

  def map(laps: Seq[LapInput]): Seq[GroupedLapInput]

  protected def toMillis(str: String) = {
    val timeParts = str.split(':')
    val ms = timeParts(1).split('.')
    Duration.millis(
      timeParts(0).toLong * 60 * 1000 + ms(0).toLong * 1000 + ms(1).toLong
    )
  }
}