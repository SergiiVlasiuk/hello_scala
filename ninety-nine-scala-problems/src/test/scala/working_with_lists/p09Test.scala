package working_with_lists

import org.scalatest.FunSpec

class p09Test extends FunSpec {
  val input: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  val expected: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

  describe("p09Test") {
    it("should pack") {
      assertResult(expected)(p09.pack(input))
    }
  }
}
