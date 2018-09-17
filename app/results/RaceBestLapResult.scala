package results

import javax.inject.{Inject, Singleton}

import mappers.RaceBestLapMapper
import models.{GroupedLapInput, LapInput}

@Singleton
class RaceBestLapResult @Inject()(mapper: RaceBestLapMapper) extends BaseResult {
  override def name = "raceBestLap"
  override def process(laps: Seq[LapInput]): Seq[GroupedLapInput] = laps match {
    case Nil => Seq()
    case _ => mapper.map(laps)
  }
}