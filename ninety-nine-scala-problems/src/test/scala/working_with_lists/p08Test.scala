package working_with_lists

import org.scalatest.FunSpec

class p08Test extends FunSpec {
  val input = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  val expected: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

  describe("p08Test") {

    it("should compressTailRecursive") {
      assertResult(expected)(p08.compressTailRecursive(input))
    }

    it("should compressFunctional") {
      assertResult(expected)(p08.compressFunctional(input))
    }

    it("should compressRecursive") {
      assertResult(expected)(p08.compressRecursive(input))
    }

  }
}
