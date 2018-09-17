package mappers

import org.scalatestplus.play.PlaySpec

import utils.LapInputFakes

class RaceBestLapMapperSpec extends PlaySpec {

  val mapper = new RaceBestLapMapper

  "RaceBestLapMapper" must {

    "return an empty GroupedLapInput seq given an empty LapInput seq" in {
      val grouped = mapper.map(List())
      grouped.size mustBe 0
    }

    "return a valid GroupedLapInput seq given a valid LapInput seq" in {
      val laps = LapInputFakes.validSeq
      val grouped = mapper.map(LapInputFakes.validSeq)

      grouped.size mustBe 1
      grouped.tail.size mustBe 0
      grouped.head.finalLap.pilotName mustBe "F.MASSA"
    }
  }
}