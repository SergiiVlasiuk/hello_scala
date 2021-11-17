package working_with_lists

import org.scalatest.FunSpec

class p05Test extends FunSpec {
  val input: List[Int] = List(1, 1, 2, 3, 5, 8)
  val expected: List[Int] = input.reverse

  describe("p05Test") {

    it("should reverseTailRecursive") {
      assertResult(expected)(p05.reverseTailRecursive(input))
    }

    it("should reverseRecursive") {
      assertResult(expected)(p05.reverseRecursive(input))
    }

    it("should reverseFunctional") {
      assertResult(expected)(p05.reverseFunctional(input))
    }

    it("should reverseBuiltin") {
      assertResult(expected)(p05.reverseBuiltin(input))
    }

  }
}
