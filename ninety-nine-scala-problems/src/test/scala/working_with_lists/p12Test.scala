package working_with_lists

import org.scalatest.FunSpec

class p12Test extends FunSpec {
  val input: List[(Int, Symbol)] = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
  val expected: List[Symbol]     = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

  describe("p12Test") {

    it("should decode") {
      assertResult(expected)(p12.decode(input))
    }
  }
}
