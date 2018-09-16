package controllers.validators

import controllers.utils.{Messages, Requests}
import org.scalatestplus.play.PlaySpec

class LapResultsRequestValidatorSpec extends PlaySpec {

  val validator = new LapResultsRequestValidator

  "LapResultsRequestValidator" must {

    "return error (Left) when empty request is given" in {
      val either = validator.validate(Requests.empty)
      either mustBe ('left)
    }

    "return error (Left) when just the header is given" in {
      val either = validator.validate(Requests.justHeader)
      either mustBe ('left)
      either.left.get mustEqual Messages.requestMustContainerHeaderAndLaps
    }

    "return error (Left) when no header is given" in {
      val either = validator.validate(Requests.noHeader)
      either mustBe ('left)
      either.left.get mustEqual Messages.headerNotFoundOnRequest
    }
  }
}
