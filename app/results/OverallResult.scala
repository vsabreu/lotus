package results

import javax.inject.{Inject, Singleton}
import mappers.GroupedLapInputMapper
import models.{GroupedLapInput, LapInput}
import play.api.libs.json.{JsArray, JsValue, Json}

@Singleton
class OverallResult @Inject()(mapper: GroupedLapInputMapper) extends BaseResult {

  override def name = "overall"

  override def process(laps: Seq[LapInput]): JsValue = laps match {
    case Nil => JsArray.empty
    case _ => {

      val pilots = mapper.map(laps).sortBy(_.totalTime.getMillis)
      val pilotsFinalLaps = finalLaps(pilots)

      val results = ((1 to pilotsFinalLaps.size) zip pilotsFinalLaps).map {
        case (rank, pilot) =>
        Json.obj(
          "rank" -> rank,
          "pilot" -> Json.obj("name" -> pilot.lap.pilotName, "code" -> pilot.lap.pilotCode),
          "totalLaps" -> pilot.lap.lap,
          "totalTime" -> pilot.totalTime.toString
        )
      }
      JsArray(results)
    }
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