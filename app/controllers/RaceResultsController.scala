package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class RaceResultsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def results() = Action { implicit request =>
    Ok("Results will be returned here.")
  }
}