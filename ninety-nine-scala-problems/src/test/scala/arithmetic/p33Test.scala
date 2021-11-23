package arithmetic

import org.scalatest.FunSuite
import arithmetic.p33._

class p33Test extends FunSuite {

  test("testIsCoprimeTo") {
    assertResult(35.isCoprimeTo(64))(true)
  }
}
