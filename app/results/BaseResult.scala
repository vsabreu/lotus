package results

import models.{GroupedLapInput, LapInput}

trait BaseResult {
  def name: String
  protected def process(laps: Seq[LapInput]): Seq[GroupedLapInput]

  def create(laps: Seq[LapInput]) = laps match {
    case Nil => Map[String, Seq[GroupedLapInput]]()
    case _ => Map[String, Seq[GroupedLapInput]](name -> process(laps))
  }
}