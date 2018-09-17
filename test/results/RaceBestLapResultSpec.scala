package results

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.Injecting

import utils.LapInputFakes

class RaceBestLapResultSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  val RaceBestLap = "raceBestLap"
  val raceBestLapResult = inject[RaceBestLapResult]

  "RaceBestLapResult" must {

    "return empty results given no lap inputs" in {
      val results = raceBestLapResult.create(Seq[Nothing]())
      results.size mustBe 0
    }

    "return valid results given valid input" in {
      val bestLap = Seq(LapInputFakes.validSeq.head)
      val results = raceBestLapResult.create(bestLap)

      results.keySet.exists(_ == RaceBestLap) mustBe true

      val resultData = results.get(RaceBestLap).get
      val best = resultData.head
      best.finalLap.pilotName mustBe "F.MASSA"
      best.finalLap.pilotCode mustBe "038"
    }
  }
}