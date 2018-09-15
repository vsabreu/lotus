package results

import javax.inject.Singleton
import models.LapInput
import play.api.libs.json.{JsValue, Json}

@Singleton
class DefaultResult extends BaseResult {

  override def name = "default"

  override def processResult(laps: Seq[LapInput]): JsValue = {
    Json.obj()
  }
}