package results

import models.LapInput
import play.api.libs.json.{JsValue, Json}

trait BaseResult {
  def name: String
  def processResult(laps: Seq[LapInput]): JsValue
  def createResult(laps: Seq[LapInput]) = Json.obj("resultName" -> name, "data" -> processResult(laps))
}