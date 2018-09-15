package models

case class LapInput(
  time: String,
  pilotCode: String,
  pilotName: String,
  lap: Int,
  lapTime: String,
  lapAverageSpeed: Double
)