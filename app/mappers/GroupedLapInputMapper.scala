package mappers

import javax.inject.Singleton
import models.{GroupedLapInput, LapInput}

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
      GroupedLapInput(lap, 0, summedAvg)
    }
    case None => GroupedLapInput(lap, 0, lap.lapAverageSpeed)
  }
}
