package controllers.parsers

import javax.inject.{Inject, Singleton}
import scala.collection.JavaConverters._

@Singleton
class ResultParsers @Inject()(
  parsers: java.util.Set[Parser]
) {
  def map(name: String): Option[Parser] = {
    parsers.asScala.map(p => (p.name, Some(p))).toMap.get(name).getOrElse(None)
  }
}