import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import net.codingwell.scalaguice.ScalaModule

import controllers.parsers.{OverallParser, Parser, RaceBestLapParser, ResultParsers}
import mappers.GroupedLapInputMapper
import results.{BaseResult, OverallResult, RaceBestLapResult}

class Module extends AbstractModule with ScalaModule {

  override def configure() = {
    val resultsBinder = Multibinder.newSetBinder(binder, classOf[BaseResult])
    resultsBinder.addBinding().to(classOf[RaceBestLapResult])
    resultsBinder.addBinding().to(classOf[OverallResult])

    val parserBinders = Multibinder.newSetBinder(binder, classOf[Parser])
    parserBinders.addBinding().to(classOf[RaceBestLapParser])
    parserBinders.addBinding().to(classOf[OverallParser])

    bind[GroupedLapInputMapper]
    bind[ResultParsers]
  }
}