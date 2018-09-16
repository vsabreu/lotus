package utils

import models.LapInput

object LapInputFakes {

  def validSeq() = {
    Seq(
      LapInput("23:49:08.277", "038", "F.MASSA",       1, "1:02.852", 44.275),
      LapInput("23:49:10.858", "033", "R.BARRICHELLO", 1, "1:04.352", 43.243),
      LapInput("23:49:11.075", "038", "F.MASSA",       2, "1:04.108", 43.408),
      LapInput("23:49:12.667", "033", "R.BARRICHELLO", 2, "1:04.414", 43.202),
      LapInput("23:49:13.003", "038", "F.MASSA",       3, "1:03.333", 42.703),
      LapInput("23:49:14.000", "038", "F.MASSA",       4, "1:03.213", 43.202)
    )
  }
}