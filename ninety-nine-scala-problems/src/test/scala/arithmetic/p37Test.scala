package arithmetic

import arithmetic.p37._
import org.scalatest.{Matchers, WordSpec}

class p37Test extends WordSpec with Matchers {

  "p37Test" should {

    "ThePhi" in {
      10.totient shouldBe 4
    }
  }
}
