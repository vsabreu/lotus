package controllers

import akka.stream.Materializer
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import play.api.test.{FakeRequest, Injecting}
import play.api.test.Helpers._

class RaceResultsControllerSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  implicit lazy val mat: Materializer = app.materializer

  "/api/v1/results" should {

    "returns 200 status given a valid request" in {

      val request = FakeRequest(POST, "").withTextBody("")
      val result = call(inject[RaceResultsController].results(), request)

      status(result) mustEqual OK
    }

    "returns 415 status given invalid content-type" in {
      val request = FakeRequest(POST, "").withJsonBody(Json.parse("{}"))
      val result = call(inject[RaceResultsController].results(), request)

      status(result) mustEqual UNSUPPORTED_MEDIA_TYPE
    }
  }
}