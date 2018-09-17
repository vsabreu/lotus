import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import controllers.parsers.{OverallParser, Parser, ResultParsers}
import net.codingwell.scalaguice.ScalaModule
import mappers.GroupedLapInputMapper
import results.{BaseResult, OverallResult}

class Module extends AbstractModule with ScalaModule {

  override def configure() = {
    val resultsBinder = Multibinder.newSetBinder(binder, classOf[BaseResult])
    resultsBinder.addBinding().to(classOf[OverallResult])

    val parserBinders = Multibinder.newSetBinder(binder, classOf[Parser])
    parserBinders.addBinding().to(classOf[OverallParser])

    bind[GroupedLapInputMapper]
    bind[ResultParsers]
  }
}