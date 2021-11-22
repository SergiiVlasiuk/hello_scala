package working_with_lists

import org.scalatest.FunSuite

class p26Test extends FunSuite {

  test("testCombinations") {
    val input: List[Symbol]          = List('a, 'b, 'c, 'd)
    val expected: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'c, 'd), List('b, 'c, 'd))

    assertResult(expected)(p26.combinations(3, input))
  }

  test("testCombinations2") {
    val input: List[Symbol]          = List('a, 'b, 'c, 'd, 'e)
    val expected: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), List('a, 'c, 'd),
      List('a, 'c, 'e), List('a, 'd, 'e), List('b, 'c, 'd), List('b, 'c, 'e), List('b, 'd, 'e), List('c, 'd, 'e))

    assertResult(expected)(p26.combinations(3, input))
  }
}
