package controllers.parsers

import javax.inject.{Inject, Singleton}
import models.GroupedLapInput
import play.api.libs.json.{JsValue, Json}

@Singleton
class RaceBestLapParser @Inject()() extends Parser {
  override def name = "raceBestLap"
  override def parse(pilots: Seq[GroupedLapInput]): JsValue = pilots match {
    case best :: Nil => {
      val result = Json.obj(
        "time" -> best.finalLap.lapTime,
        "pilot" -> Json.obj(
          "name" -> best.finalLap.pilotName,
          "code" -> best.finalLap.pilotCode
        )
      )
      Json.obj(name -> result)
    }
    case _ => Json.obj(name -> Json.obj())
  }
}