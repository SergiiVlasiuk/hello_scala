package working_with_lists

import org.scalatest.FunSpec

class p18Test extends FunSpec {
  val input: List[Symbol] = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
  val expected: List[Symbol] = List('d, 'e, 'f, 'g)

  describe("p18Test") {

    it("should sliceBuiltin") {
      assertResult(expected)(p18.sliceBuiltin(3, 7, input))
    }

    it("should sliceTailRecursive") {
      assertResult(expected)(p18.sliceTailRecursive(3, 7, input))
    }

    it("should sliceFunctional2") {
      assertResult(expected)(p18.sliceFunctional2(3, 7, input))
    }

    it("should sliceFunctional") {
      assertResult(expected)(p18.sliceFunctional(3, 7, input))
    }

    it("should sliceRecursive") {
      assertResult(expected)(p18.sliceRecursive(3, 7, input))
    }

    it("should sliceTailRecursive2") {
      assertResult(expected)(p18.sliceTailRecursive2(3, 7, input))
    }
  }
}
