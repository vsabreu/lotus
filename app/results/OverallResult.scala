package results

import javax.inject.{Inject, Singleton}
import mappers.GroupedLapInputMapper
import models.LapInput
import play.api.libs.json.{JsArray, JsValue}

@Singleton
class OverallResult @Inject()(mapper: GroupedLapInputMapper) extends BaseResult {

  override def name = "overall"

  override def process(laps: Seq[LapInput]): JsValue = laps match {
    case Nil => JsArray.empty
    case _ => JsArray.empty
  }
}