package models

import org.joda.time.Duration

case class GroupedLapInput(
  finalLap: LapInput,
  bestLap: Option[LapInput],
  totalTime: Duration,
  averageSpeed: Option[Double]
)