package controllers.parsers

import javax.inject.Singleton
import models.GroupedLapInput
import play.api.libs.json.{JsArray, JsValue, Json}

@Singleton
class OverallParser extends Parser {
  override def name = "overall"
  override def parse(pilots: Seq[GroupedLapInput]): JsValue = {
    val results = ((1 to pilots.size) zip pilots).map {
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
