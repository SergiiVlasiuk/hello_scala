package working_with_lists

import org.scalatest.FunSpec

class p16Test extends FunSpec {
  val input: List[Symbol] = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
  val expected: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  describe("p16Test") {
    it("should dropFunctional") {
      println(s"input.zipWithIndex: ${input.zipWithIndex}") // input.zipWithIndex: List(('a,0), ('b,1), ('c,2), ('d,3), ('e,4), ('f,5), ('g,6), ('h,7), ('i,8), ('j,9), ('k,10))
      assertResult(expected)(p16.dropFunctional(3, input))
    }

    it("should dropRecursive") {
      assertResult(expected)(p16.dropRecursive(3, input))
    }

    it("should dropTailRecursive") {
      assertResult(expected)(p16.dropTailRecursive(3, input))
    }
  }
}
