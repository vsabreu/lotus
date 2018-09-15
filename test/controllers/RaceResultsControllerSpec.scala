package controllers

import akka.stream.Materializer
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.{FakeRequest, Injecting}
import play.api.test.Helpers._

class RaceResultsControllerSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  implicit lazy val mat: Materializer = app.materializer

  "/api/v1/results" should {

    "OK given a valid request" in {

      val request = FakeRequest(POST, "").withTextBody("")
      val result = call(inject[RaceResultsController].results(), request)
      status(result) mustEqual OK
    }
  }
}