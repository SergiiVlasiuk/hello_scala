package working_with_lists

import org.scalatest.FunSuite

class p21Test extends FunSuite {
  val input: List[Symbol] = List('a, 'b, 'c, 'd)
  val expected: List[Symbol] = List('a, 'new, 'b, 'c, 'd)

  test("testInsertAt") {
    assertResult(expected)(p21.insertAt('new, 1, input))
  }

}
