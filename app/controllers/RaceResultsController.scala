package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._

import scala.collection.JavaConverters._
import scala.concurrent.Future
import contexts.DefaultContext
import controllers.parsers.ResultParsers
import controllers.validators.LapResultsRequestValidator
import results.BaseResult

@Singleton
class RaceResultsController @Inject()(
  cc: ControllerComponents,
  context: DefaultContext,
  lapValidator: LapResultsRequestValidator,
  results: java.util.Set[BaseResult],
  parsers: ResultParsers
) extends AbstractController(cc) {

  implicit val executionContext = context.cpulookup

  def raceresults() = Action.async(parseLapResultsRequest) { implicit request =>

    val response = Json.obj("results" -> results.asScala.map(r => {
      val result = r.create(request.body).get(r.name).get
      parsers.map(r.name) match {
        case Some(p) => p.parse(result)
        case None => Json.obj()
      }
    }))

    Future.successful(Ok(response))
  }

  private def parseLapResultsRequest = parse.using { _ =>
    parse.tolerantText.validate { rq =>
      lapValidator.validate(rq).left.map(BadRequest(_))
    }
  }
}