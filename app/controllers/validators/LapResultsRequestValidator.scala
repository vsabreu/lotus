package controllers.validators

import javax.inject.Inject
import models.LapInput

@Inject
class LapResultsRequestValidator {

  def validate(request: String): Either[String, Seq[LapInput]] = {
    request.split("\n").toSeq match {
      case l if l.size <= 1 => Left("Request must contain header and laps data")
      case l if l.head.matches(LapLinePatterns.regexp.regex) => Left ("Header not found on request")
      case l => l.tail.foldLeft[Either[String, Seq[LapInput]]](Right(List[LapInput]()))(reducer)
    }
  }

  private def reducer(acc: Either[String, Seq[LapInput]], line: String) = for {
    a <- acc
    lap <- lapLineParser(line)
  } yield a :+ lap

  private def lapLineParser(line: String) = line match {
    case LapLinePatterns.regexp(time, pilot, lap, lapTime, avgSpeed) => {
      val cp = pilot.split("\\s")
      Right(LapInput(time,
        cp.head, cp.last, lap.toInt, lapTime,
        avgSpeed.replace(",", ".").toDouble)
      )
    }
    case _ => Left(s"Line does not match input pattern. Line = ${line}")
  }
}