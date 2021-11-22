package working_with_lists

import org.scalatest.FunSuite

class p24Test extends FunSuite {
  val inputMax: Int = 49
  val expectedLength: Int = 3

  test("testLotto") {
    assertResult(expectedLength)(p24.lotto(expectedLength, inputMax).length)
  }

}
