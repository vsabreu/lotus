package mappers

import javax.inject.Singleton
import models.{GroupedLapInput, LapInput}

@Singleton
class RaceBestLapMapper extends BaseMapper {
  def map(laps: Seq[LapInput]): Seq[GroupedLapInput] = laps match {
    case Nil => Seq()
    case _ => {
      val bestLap = laps.sortBy(l => toMillis(l.lapTime).getMillis).head
      Seq(GroupedLapInput(bestLap, None, toMillis(bestLap.lapTime), None))
    }
  }
}