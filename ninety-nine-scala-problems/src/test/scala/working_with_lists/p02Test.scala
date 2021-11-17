package working_with_lists

import org.scalatest.FunSpec

class p02Test extends FunSpec {
  val expected: Int = 5
  val input: List[Int] = List(1, 1, 2, 3, expected, 8)

  describe("p02Test") {

    it("should lastNthBuiltin") {
      assertResult(expected)(p02.lastNthBuiltin(2, input))
    }

    it("should penultimateRecursive") {
      assertResult(expected)(p02.penultimateRecursive(input))
    }

    it("should lastNthRecursive") {
      assertResult(expected)(p02.lastNthRecursive(2, input))
    }

    it("should penultimateBuiltin") {
      assertResult(expected)(p02.penultimateBuiltin(input))
    }

  }
}
