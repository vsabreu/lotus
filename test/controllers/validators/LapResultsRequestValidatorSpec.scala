package controllers.validators

import utils.{Messages, Requests}
import org.scalatestplus.play.PlaySpec

class LapResultsRequestValidatorSpec extends PlaySpec {

  val validator = new LapResultsRequestValidator

  "LapResultsRequestValidator" must {

    "return error (Left) when empty request is given" in {
      val either = validator.validate(Requests.empty)
      either mustBe 'left
    }

    "return error (Left) when just the header is given" in {
      val either = validator.validate(Requests.justHeader)
      either mustBe 'left
      either.left.get mustEqual Messages.requestMustContainerHeaderAndLaps
    }

    "return error (Left) when no header is given" in {
      val either = validator.validate(Requests.noHeader)
      either mustBe 'left
      either.left.get mustEqual Messages.headerNotFoundOnRequest
    }

    "return valid data (Right) when a valid request is given" in {
      val either = validator.validate(Requests.valid)
      either mustBe 'right

      val laps = either.right.get
      laps.size mustBe 1
      laps.head.pilotName mustEqual "F.MASSA"
      laps.head.pilotCode mustEqual "038"
    }

    "return error (Left) when request with wrong pattern is given" in {
      val either = validator.validate(Requests.withWrongPattern)
      either mustBe 'left
      either.left.get must include (Messages.lineDoesNotMatchInputPattern)
    }
  }
}
