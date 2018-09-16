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
      val result = performRequest(Requests.valid)
      status(result) mustEqual OK
    }

    "return 400 given an empty request" in {
      val result = performRequest(Requests.empty)
      status(result) mustEqual BAD_REQUEST
      contentAsString(result) mustEqual Messages.requestMustContainerHeaderAndLaps
    }

    "return 400 given a request without header" in {
      val result = performRequest(Requests.noHeader)
      status(result) mustEqual BAD_REQUEST
      contentAsString(result) mustEqual Messages.headerNotFoundOnRequest
    }
  }

  private def performRequest(body: String) = {
    val request = FakeRequest(POST, "").withTextBody(body)
    call(inject[RaceResultsController].results(), request)
  }
}