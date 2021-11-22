package working_with_lists

import org.scalatest.FunSpec

class p15Test extends FunSpec {
  val input: List[Symbol] = List('a, 'b, 'c, 'c, 'd)
  val expected: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)

  describe("p15Test") {
    it("should no duplicate (1)") {
      assertResult(input)(p15.duplicateN(1, input))
    }

    it("should duplicate 2") {
      assertResult(List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))(p15.duplicateN(2, input))
    }

    it("should duplicate 3") {
      assertResult(expected)(p15.duplicateN(3, input))
    }
  }
}
