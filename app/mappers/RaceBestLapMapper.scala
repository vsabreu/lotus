package mappers

import javax.inject.Singleton
import models.{GroupedLapInput, LapInput}

@Singleton
class RaceBestLapMapper extends BaseMapper {

  def map(laps: Seq[LapInput]): Seq[GroupedLapInput] = {
    val bestLap = laps.sortBy(l => toMillis(l.lapTime).getMillis).head
    Seq(
      GroupedLapInput(bestLap, toMillis(bestLap.lapTime), 0)
    )
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