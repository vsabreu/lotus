package controllers

import akka.stream.Materializer
import controllers.utils.{Messages, Requests}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.{FakeRequest, Injecting}
import play.api.test.Helpers._

class RaceResultsControllerSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  implicit lazy val mat: Materializer = app.materializer

  "/api/v1/results" must {

    "return 200 given a valid request" in {
      val request = FakeRequest(POST, "").withTextBody(Requests.valid)
      val result = call(inject[RaceResultsController].results(), request)

      status(result) mustEqual OK
    }

    "return 400 given an empty request" in {
      val request = FakeRequest(POST, "").withTextBody(Requests.empty)
      val result = call(inject[RaceResultsController].results(), request)

      status(result) mustEqual BAD_REQUEST
      contentAsString(result) mustEqual Messages.requestMustContainerHeaderAndLaps
    }

    "return 400 given an request without header" in {
      val request = FakeRequest(POST, "").withTextBody(Requests.noHeader)
      val result = call(inject[RaceResultsController].results(), request)

      status(result) mustEqual BAD_REQUEST
      contentAsString(result) mustEqual Messages.headerNotFoundOnRequest
    }
  }
}