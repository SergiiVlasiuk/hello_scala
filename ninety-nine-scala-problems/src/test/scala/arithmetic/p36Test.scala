package arithmetic

import org.scalatest.{Matchers, WordSpec}
import arithmetic.p36._

class p36Test extends WordSpec with Matchers {

  "p36Test" should {

    "Determine the prime factors of a given positive integer (2)" in {
      315.primeFactorMultiplicity shouldBe Map(3 -> 2, 5 -> 1, 7 -> 1)
    }
  }
}
