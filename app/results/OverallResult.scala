package results

import javax.inject.Singleton
import models.LapInput
import play.api.libs.json.{JsArray, JsValue, Json}

@Singleton
class OverallResult extends BaseResult {

  override def name = "overall"

  override def process(laps: Seq[LapInput]): JsValue = laps match {
    case Nil => JsArray.empty
    case _ => JsArray.empty
  }
}