package working_with_lists

import org.scalatest.FunSuite

class p22Test extends FunSuite {
  val expected: List[Int] = List(4, 5, 6, 7, 8, 9)

  test("testRangeTailRecursive") {
    assertResult(expected)(p22.rangeTailRecursive(4, 9))
  }

  test("testRangeBuiltin") {
    assertResult(expected)(p22.rangeBuiltin(4, 9))
  }

  test("testRangeFunctional") {
    assertResult(expected)(p22.rangeFunctional(4, 9))
  }

  test("testRangeRecursive") {
    assertResult(expected)(p22.rangeRecursive(4, 9))
  }
}
