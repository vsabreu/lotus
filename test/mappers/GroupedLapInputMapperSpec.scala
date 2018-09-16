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

      val sumAvgSpd = laps.filter(_.pilotCode == "038")
        .map(_.lapAverageSpeed)
        .foldLeft(0.0) { (acc, i) => acc + i } / 2

      grouped.head.averageSpeed mustEqual sumAvgSpd
      grouped.head.lap.pilotName mustBe "F.MASSA"
    }
  }
}
