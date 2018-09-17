package mappers

import javax.inject.Singleton
import models.{GroupedLapInput, LapInput}

@Singleton
class GroupedLapInputMapper extends BaseMapper {

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
}