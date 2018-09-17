package controllers.parsers

import javax.inject.{Inject, Singleton}
import models.GroupedLapInput
import play.api.libs.json.{JsArray, JsValue, Json}

@Singleton
class OverallParser @Inject()() extends Parser {
  override def name = "overall"
  override def parse(pilots: Seq[GroupedLapInput]): JsValue = {

    val champTime = pilots.head.totalTime
    val results = ((1 to pilots.size) zip pilots).map {
      case (rank, pilot) =>
        Json.obj(
          "rank" -> rank,
          "pilot" -> Json.obj("name" -> pilot.finalLap.pilotName, "code" -> pilot.finalLap.pilotCode),
          "laps" -> Json.obj("total" -> pilot.finalLap.lap, "best" -> pilot.bestLap.get.lapTime),
          "totalTime" -> pilot.totalTime.toString,
          "averageSpeed" -> pilot.averageSpeed,
          "timeAfterChamp" -> pilot.totalTime.minus(champTime).toString
        )
    }
    Json.obj(name -> JsArray(results))
  }
}