package working_with_lists

import org.scalatest.FunSpec

class p03Test extends FunSpec {
//  nth(2,
  val zero: Int = 1
  val first: Int = 1
  val second: Int = 2
  val third: Int = 3
  val fourth: Int = 5
  val fifth: Int = 8

  val input: List[Int] =
//    List(1, 1, 2, 3, 5, 8)
    List(zero, first, second, third, fourth, fifth)

  describe("p03Test") {

    describe("should nthRecursive") {
      it("zero") {
        assertResult(zero)(p03.nthRecursive(0, input))
      }
      it("first") {
        assertResult(first)(p03.nthRecursive(1, input))
      }
      it("second") {
        assertResult(second)(p03.nthRecursive(2, input))
      }
      it("third") {
        assertResult(third)(p03.nthRecursive(3, input))
      }
      it("fourth") {
        assertResult(fourth)(p03.nthRecursive(4, input))
      }
      it("fifth") {
        assertResult(fifth)(p03.nthRecursive(5, input))
      }
    }

    describe("should nthBuiltin") {
      it("zero") {
        assertResult(zero)(p03.nthBuiltin(0, input))
      }
      it("first") {
        assertResult(first)(p03.nthBuiltin(1, input))
      }
      it("second") {
        assertResult(second)(p03.nthBuiltin(2, input))
      }
      it("third") {
        assertResult(third)(p03.nthBuiltin(3, input))
      }
      it("fourth") {
        assertResult(fourth)(p03.nthBuiltin(4, input))
      }
      it("fifth") {
        assertResult(fifth)(p03.nthBuiltin(5, input))
      }
    }

  }
}
