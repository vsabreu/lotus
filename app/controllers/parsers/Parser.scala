package controllers.parsers

import play.api.libs.json.JsValue

import models.GroupedLapInput

trait Parser {
  def name: String
  def parse(pilots: Seq[GroupedLapInput]): JsValue
}