package results

import models.LapInput
import play.api.libs.json.{JsValue, Json}

trait BaseResult {
  def name: String
  def process(laps: Seq[LapInput]): JsValue
  def create(laps: Seq[LapInput]) = Json.obj("resultName" -> name, "data" -> process(laps))
}