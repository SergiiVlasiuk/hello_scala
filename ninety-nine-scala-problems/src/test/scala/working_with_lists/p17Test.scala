package working_with_lists

import org.scalatest.FunSpec

class p17Test extends FunSpec {
  val input: List[Symbol] = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
  val expected: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c), List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

  describe("p17Test") {

    it("should splitRecursive") {
      assertResult(expected)(p17.splitRecursive(3, input))
    }

    it("should splitTailRecursive") {
      assertResult(expected)(p17.splitTailRecursive(3, input))
    }

    it("should splitFunctional") {
      assertResult(expected)(p17.splitFunctional(3, input))
    }

    it("should splitBuiltin") {
      assertResult(expected)(p17.splitBuiltin(3, input))
    }
  }
}
