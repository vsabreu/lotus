package controllers.validators

protected object LapLinePatterns {
  private val time = """\d{2}\:\d{2}\:\d{2}\.\d{3}"""
  private val pilotCodeAndName = """\d{3}\s.\s\w*\.*\w*"""
  private val lap = """\d+"""
  private val lapTime = """\d+\:\d{2}\.\d{3}"""
  private val averageSpeed = """\d+\,*\d*"""
  private val column = """\s{2,}"""

  val regexp = List(
    time,
    pilotCodeAndName,
    lap,
    lapTime,
    averageSpeed).map { p => s"($p)" }.mkString(column).r
}