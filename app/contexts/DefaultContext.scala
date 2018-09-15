package contexts

import akka.actor.ActorSystem
import javax.inject.{Inject, Singleton}

@Singleton
class DefaultContext @Inject()(akkaSystem: ActorSystem, configuration: play.api.Configuration){
  lazy val cpulookup = akkaSystem.dispatchers.lookup("contexts.cpu-ops")
}