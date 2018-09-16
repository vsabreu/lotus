package models

import org.joda.time.Duration

case class GroupedLapInput(lap: LapInput, totalTime: Duration, averageSpeed: Double)