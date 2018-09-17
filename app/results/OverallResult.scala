package results

import javax.inject.{Inject, Singleton}

import mappers.GroupedLapInputMapper
import models.{GroupedLapInput, LapInput}

@Singleton
class OverallResult @Inject()(mapper: GroupedLapInputMapper) extends BaseResult {
  override def name = "overall"
  override def process(laps: Seq[LapInput]): Seq[GroupedLapInput] = laps match {
    case Nil => Seq()
    case _ => finalLaps(mapper.map(laps)).sortBy(_.totalTime.getMillis)
  }

  private def finalLaps(laps: Seq[GroupedLapInput]): Seq[GroupedLapInput] = {
    val lapsByPilotCode = laps.groupBy(_.lap.pilotCode)
    laps.find(_.lap.lap == 4) match {
      case Some(w) => {
        w +: lapsByPilotCode
          .filterNot(_._1 == w.lap.pilotCode)
          .flatMap { case (_, lap) => lap }
          .toSeq
      }
      case None => lapsByPilotCode.flatMap { case (_, lap) => lap }.toSeq
    }
  }
}