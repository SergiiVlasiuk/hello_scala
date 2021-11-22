package working_with_lists

import org.scalatest.FunSuite

class p25Test extends FunSuite {
  val input: List[Symbol] = List('a, 'b, 'c, 'd, 'e, 'f)
  val expected: List[Symbol] = List('b, 'a, 'd, 'c, 'e, 'f)

//  test("testRandomPermute") {
//    assertResult(expected)(p25.randomPermute(input))
//  }
//
//  test("testRandomPermute1") {
//    assertResult(expected)(p25.randomPermute1(input))
//  }

}
