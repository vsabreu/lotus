package mappers

import javax.inject.Singleton

import models.{GroupedLapInput, LapInput}

@Singleton
class GroupedLapInputMapper extends BaseMapper {

  def map(laps: Seq[LapInput]): Seq[GroupedLapInput] = {

    val bestLaps = laps.map(l => (l.pilotCode -> laps
      .filter(_.pilotCode == l.pilotCode)
      .sortBy(l => toMillis(l.lapTime).getMillis).head)).toMap

    val groupedLaps = laps.foldLeft(Map[String, GroupedLapInput]()) { (acc, lap) =>
      val bestLap = bestLaps.get(lap.pilotCode).get
      val grouped = group(acc.get(lap.pilotCode), bestLap, lap)
      acc + (lap.pilotCode -> grouped)
    }
    groupedLaps.values.toSeq
  }

  private def group(key: Option[GroupedLapInput], bestLap: LapInput, lap: LapInput) = key match {
    case Some(k) => {
      val summedAvg = (k.averageSpeed.getOrElse(Double.NaN) + lap.lapAverageSpeed) / 2
      val totalTime = k.totalTime.plus(toMillis(lap.lapTime))
      GroupedLapInput(lap, Some(bestLap), totalTime, Some(summedAvg))
    }
    case None =>
      GroupedLapInput(lap, Some(bestLap), toMillis(lap.lapTime), Some(lap.lapAverageSpeed))
  }
}