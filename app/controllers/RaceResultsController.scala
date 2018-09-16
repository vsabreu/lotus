package controllers

import contexts.DefaultContext
import controllers.validators.LapResultsRequestValidator
import javax.inject._
import play.api.mvc._
import results.BaseResult

import scala.concurrent.Future

@Singleton
class RaceResultsController @Inject()(
  cc: ControllerComponents,
  context: DefaultContext,
  lapValidator: LapResultsRequestValidator,
  results: java.util.Set[BaseResult]

) extends AbstractController(cc) {

  implicit val executionContext = context.cpulookup

  def raceresults() = Action.async(parseLapResultsRequest) { implicit request =>
    Future.successful(Ok("Results will be returned here."))
  }

  private def parseLapResultsRequest = parse.using { _ =>
    parse.tolerantText.validate { rq =>
      lapValidator.validate(rq).left.map(BadRequest(_))
    }
  }
}