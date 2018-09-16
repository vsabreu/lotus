package mappers

import javax.inject.Singleton
import models.{GroupedLapInput, LapInput}
import org.joda.time.Duration

@Singleton
class GroupedLapInputMapper {

  def map(laps: Seq[LapInput]): Seq[GroupedLapInput] = {
    val groupedLaps = laps.foldLeft(Map[String, GroupedLapInput]()) { (acc, lap) =>
      val grouped = group(acc.get(lap.pilotCode), lap)
      acc + (lap.pilotCode -> grouped)
    }
    groupedLaps.values.toSeq
  }

  private def group(key: Option[GroupedLapInput], lap: LapInput) = key match {
    case Some(k) => {
      val summedAvg = (k.averageSpeed + lap.lapAverageSpeed) / 2
      val totalTime = k.totalTime.plus(toMillis(lap.lapTime))
      GroupedLapInput(lap, totalTime, summedAvg)
    }
    case None =>
      GroupedLapInput(lap, toMillis(lap.lapTime), lap.lapAverageSpeed)
  }

  private def toMillis(str: String) = {
    val timeParts = str.split(':')
    val ms = timeParts(1).split('.')
    Duration.millis(
      timeParts(0).toLong * 60 * 1000 + ms(0).toLong * 1000 + ms(1).toLong
    )
  }
}