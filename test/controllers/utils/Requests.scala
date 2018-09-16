package controllers.utils

object Requests {
  val empty = ""
  val justHeader = "Hora    Piloto    Nº Volta    Tempo    Volta    Velocidade média da volta"

  val noHeader = s"""|23:49:08.277    038 – F.MASSA          1    1:02.852    44,275
                     |23:49:10.858    033 – R.BARRICHELLO    1    1:04.352    43,243""".stripMargin

  val valid = s"""|${justHeader}
       |23:49:08.277    038 – F.MASSA          1    1:02.852    44,275
       |23:49:10.858    033 – R.BARRICHELLO    1    1:04.352    43,243""".stripMargin
}
