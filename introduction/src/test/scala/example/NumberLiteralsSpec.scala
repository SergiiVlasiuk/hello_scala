package example

import org.scalatest._

class NumberLiteralsSpec extends FlatSpec with Matchers {
  "Integer literals are 32-bit and can be created from decimals as well as hexadecimals" should "be" in {
    val a = 2
    val b = 31
    val c = 0x30F
    val e = 0
    val f = -2
    val g = -31
    val h = -0x30F
    a should be(2)
    b should be(31)
    c should be(783) //Hint: 30F = 783
    e should be(0)
    f should be(-2)
    g should be(-31)
    h should be(-783)
  }
  "Long literals are 64-bit. They are specified by appending an L or l at the end of the declaration" should "be" in {
    val a = 2L
    val b = 31L
    val c = 0x30FL
    val e = 0L
    val f = -2l
    val g = -31L
    val h = -0x30FL

    a should be(2)
    b should be(31)
    c should be(783) //Hint: 30F = 783
    e should be(0)
    f should be(-2)
    g should be(-31)
    h should be(-783)
  }
  "Float and Double literals conform to IEEE-754. Floats are 32-bit, while doubles are 64-bit" should "be" in {
    val a = 3.0
    val b = 3.00
    val c = 2.73
    val d = 3f
    val e = 3.22d
    val f = 93e-9
    val g = 93E-9
    val h = 0.0
    val i = 9.23E-9D

    a should be(3.0)
    b should be(3.0)
    c should be(2.73)
    d should be(3.0)
    e should be(3.22)
    f should be(9.3E-8)
    g should be(9.3E-8)
    h should be(0)
    i should be(9.23E-9)
  }
}
