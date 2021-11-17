package working_with_lists

import org.scalatest.FunSpec

class p10Test extends FunSpec {
  val input: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  val expected: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

  describe("p10Test") {
    it("should encode") {
      assertResult(expected)(p10.encode(input))
    }
  }
}
