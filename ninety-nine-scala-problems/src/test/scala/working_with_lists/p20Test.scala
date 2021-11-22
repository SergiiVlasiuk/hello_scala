package working_with_lists

import org.scalatest.FunSpec

class p20Test extends FunSpec {
  val input: List[Symbol] = List('a, 'b, 'c, 'd)
  val expected: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  describe("p20Test") {

    it("should removeAt") {
      assertResult(expected)(p20.removeAt(1, input))
    }

    it("should removeAt2") {
      assertResult(expected)(p20.removeAt2(1, input))
    }
  }
}
