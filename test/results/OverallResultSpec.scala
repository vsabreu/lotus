package results

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.JsArray
import play.api.test.Injecting

class OverallResultSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  val overallResult = inject[OverallResult]

  "OverallResult" must {

    "empty results given no lap inputs" in {
      val results = overallResult.create(List())

      (results \ "resultName").as[String] mustBe "overall"
      (results \ "data").get.as[JsArray].value.size mustBe 0
    }
  }
}