package controllers.parsers

import models.GroupedLapInput
import play.api.libs.json.JsValue

trait Parser {
  def name: String
  def parse(pilots: Seq[GroupedLapInput]): JsValue
}
