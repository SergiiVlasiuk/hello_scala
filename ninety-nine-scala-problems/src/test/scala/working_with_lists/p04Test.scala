package working_with_lists

import org.scalatest.FunSpec

class p04Test extends FunSpec {
  val expected: Int = 6
  val input: List[Int] = List(1, 1, 2, 3, 5, 8)

  describe("p04Test") {

    it("should lengthRecursive") {
      assertResult(expected)(p04.lengthRecursive(input))
    }

    it("should lengthRecursive2") {
      assertResult(expected)(p04.lengthRecursive2(input))
    }

    it("should lengthFunctional") {
      assertResult(expected)(p04.lengthFunctional(input))
    }

    it("should lengthTailRecursive") {
      assertResult(expected)(p04.lengthTailRecursive(input))
    }

    it("should lengthBuiltin") {
      assertResult(expected)(p04.lengthBuiltin(input))
    }

  }
}
