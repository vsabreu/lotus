import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import mappers.GroupedLapInputMapper
import net.codingwell.scalaguice.ScalaModule
import results.{BaseResult, OverallResult}

class Module extends AbstractModule with ScalaModule {

  override def configure() = {
    val multiBinder = Multibinder.newSetBinder(binder, classOf[BaseResult])
    multiBinder.addBinding().to(classOf[OverallResult])

    bind[GroupedLapInputMapper]
  }
}