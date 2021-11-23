package arithmetic

import org.scalatest.{Matchers, WordSpec}
import arithmetic.p34._

class p34Test extends WordSpec with Matchers {
  "P34" should {
    "Calculate Euler's totient function phi(m)" in {
      10.totient shouldBe 4
    }

    "Calculate Euler's totient2 function phi(m)" in {
      10.totient2 shouldBe 4
    }
  }
}
