package arithmetic

import org.scalatest.FunSuite
import arithmetic.p40._

class p40Test extends FunSuite {

  test("testGoldBach 28") {
    val actual = 28.goldbach
    assertResult((5, 23))(actual)
  }

  test("testGoldBach 10") {
    val actual = 10.goldbach
    assertResult((3, 7))(actual)
  }
}
