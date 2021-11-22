package working_with_lists

import org.scalatest.FunSuite

class p28Test extends FunSuite {
  val input: List[List[Symbol]] = List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))
  val expected: List[List[Symbol]] = List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))

  test("testLsortFreq") {
    assertResult(expected)(p28.lsortFreq(input))
  }

  test("testLsort") {
    val expectedSortByLength: List[List[Symbol]] = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))
    assertResult(expectedSortByLength)(p28.lsort(input))
  }

}
