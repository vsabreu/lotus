package mappers

import org.scalatestplus.play.PlaySpec
import utils.LapInputFakes

class GroupedLapInputMapperSpec extends PlaySpec {

  val mapper = new GroupedLapInputMapper

  "GroupedLapInputMapper" must {

    "return an empty GroupedLapInput seq given an empty LapInput seq" in {
      val grouped = mapper.map(List())
      grouped.size mustBe 0
    }

    "return a valid GroupedLapInput seq given a valid LapInput seq" in {
      val laps = LapInputFakes.validSeq
      val grouped = mapper.map(LapInputFakes.validSeq)

      grouped.size mustBe 2
      grouped.tail.size mustBe 1
      grouped.head.lap.pilotName mustBe "F.MASSA"
    }
  }
}
