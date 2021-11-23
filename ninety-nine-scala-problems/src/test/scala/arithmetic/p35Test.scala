package arithmetic

import org.scalatest.{Matchers, WordSpec}
import arithmetic.p35._

class p35Test extends WordSpec with Matchers {

  "p35Test" should {

    "PrimeFactors 315" in {
      315.primeFactors should contain theSameElementsAs List(3, 3, 5, 7)
    }

    "PrimeFactors 1321320" in {
      1321320.primeFactors should contain theSameElementsAs List(2, 2, 2, 3, 5, 7, 11, 11, 13)
    }

  }
}
