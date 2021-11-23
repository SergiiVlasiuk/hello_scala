package arithmetic

import org.scalatest.{Matchers, WordSpec}

class p39Test extends WordSpec with Matchers {

  "p39Test" should {

    "listPrimeInRange2" in {
      val actual = p39.listPrimesInRange2(7 to 31)
      actual should contain theSameElementsAs List(7, 11, 13, 17, 19, 23, 29, 31)
    }

    "listPrimesInRange" in {
      val actual = p39.listPrimesInRange(7 to 31)
      actual should contain theSameElementsAs List(7, 11, 13, 17, 19, 23, 29, 31)
    }
  }
}
