package results

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.JsArray
import play.api.test.Injecting
import utils.LapInputFakes

class OverallResultSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  val overallResult = inject[OverallResult]

  "OverallResult" must {

    "return empty results given no lap inputs" in {
      val results = overallResult.create(List())

      (results \ "resultName").as[String] mustBe "overall"
      (results \ "data").get.as[JsArray].value.size mustBe 0
    }

    "return valid results given valid input" in {
      val results = overallResult.create(LapInputFakes.validSeq)

      (results \ "resultName").as[String] mustBe "overall"

      val resultData = (results \ "data").get.as[JsArray].value
      (resultData.head \ "rank").as[Int] mustBe 1

      val winner = (resultData.head \ "pilot")
      (winner \ "name").as[String] mustBe "F.MASSA"
      (winner \ "code").as[String] mustBe "038"
    }
  }
}