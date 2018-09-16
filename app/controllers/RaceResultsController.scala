package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import scala.collection.JavaConverters._
import scala.concurrent.Future

import contexts.DefaultContext
import controllers.validators.LapResultsRequestValidator
import results.BaseResult

@Singleton
class RaceResultsController @Inject()(
  cc: ControllerComponents,
  context: DefaultContext,
  lapValidator: LapResultsRequestValidator,
  results: java.util.Set[BaseResult]

) extends AbstractController(cc) {

  implicit val executionContext = context.cpulookup

  def raceresults() = Action.async(parseLapResultsRequest) { implicit request =>
    val rs = Json.obj("results" -> results.asScala.map(r => r.create(request.body)))
    Future.successful(Ok(rs))
  }

  private def parseLapResultsRequest = parse.using { _ =>
    parse.tolerantText.validate { rq =>
      lapValidator.validate(rq).left.map(BadRequest(_))
    }
  }
}