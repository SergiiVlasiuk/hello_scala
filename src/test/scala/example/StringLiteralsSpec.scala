package example

import org.scalatest._

class StringLiteralsSpec extends FlatSpec with Matchers {
  "Character literals are quoted with single quotes" should "be" in {
    val a = 'a'
    val b = 'B'
    a.toString should be("a")
    b.toString should be("B")
  }
  "Character literals can use hexadecimal Unicode" should "be" in {
    val c = 'a' //unicode for a
    c.toString should be("a")
  }
  "Character literals can use octal as well" should "be" in {
    val d = '\141' //octal for a
    d.toString should be("a")
  }
  "Character literals can use escape sequences" should "be" in {
    val e = '\"'
    val f = '\\'
    e.toString should be("\"")
    f.toString should be("\\")
  }
  "One-line String literals are surrounded by quotation marks" should "be" in {
    val a = "To be or not to be"
    a should be("To be or not to be")
  }
}
