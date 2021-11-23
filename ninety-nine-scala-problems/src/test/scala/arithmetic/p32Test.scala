package arithmetic

import org.scalatest.FunSpec

class p32Test extends FunSpec {

  describe("p32Test") {

    it("gcd 32 and 128") {
      assertResult(32)(p32.gcd(32, 128))
    }

    it("gcd 128 and 32") {
      assertResult(32)(p32.gcd(128, 32))
    }

    it("gcd 36 and 63") {
      assertResult(9)(p32.gcd(36, 63))
    }

    it("gcd 63 and 36") {
      assertResult(9)(p32.gcd(63, 36))
    }
  }
}
