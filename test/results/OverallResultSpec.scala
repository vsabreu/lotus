package results

import org.scalatestplus.play.PlaySpec
import play.api.libs.json.{JsArray, JsValue}

class OverallResultSpec extends PlaySpec {

  val overallResult = new OverallResult

  "OverallResult" must {

    "empty results given no lap inputs" in {
      val results = overallResult.create(List())

      (results \ "resultName").as[String] mustBe "overall"
      (results \ "data").get.as[JsArray].value.size mustBe 0
    }
  }
}