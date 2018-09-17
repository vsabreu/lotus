package results

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.Injecting

import utils.LapInputFakes

class OverallResultSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  val Overall = "overall"
  val overallResult = inject[OverallResult]

  "OverallResult" must {

    "return empty results given no lap inputs" in {
      val results = overallResult.create(Seq())
      results.size mustBe 0
    }

    "return valid results given valid input" in {
      val results = overallResult.create(LapInputFakes.validSeq)

      results.keySet.exists(_ == Overall) mustBe true

      val resultData = results.get(Overall).get
      val champion = resultData.head
      champion.finalLap.pilotName mustBe "F.MASSA"
      champion.finalLap.pilotCode mustBe "038"
    }
  }
}