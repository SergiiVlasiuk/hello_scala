package working_with_lists

import org.scalatest.FunSpec

class p14Test extends FunSpec {
  val input: List[Symbol] = List('a, 'b, 'c, 'c, 'd)
  val expected: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

  describe("p14Test") {

    it("should duplicate") {
      assertResult(expected)(p14.duplicate(input))
    }
  }
}
