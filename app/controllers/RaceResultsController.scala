package controllers

import contexts.DefaultContext
import javax.inject._
import play.api.mvc._
import results.BaseResult

import scala.concurrent.Future

@Singleton
class RaceResultsController @Inject()(
  cc: ControllerComponents,
  context: DefaultContext,
  injectedResults: java.util.Set[BaseResult]

) extends AbstractController(cc) {

  implicit val executionContext = context.cpulookup

  def results() = Action.async(parse.text) { implicit request =>
    Future.successful(Ok("Results will be returned here."))
  }
}