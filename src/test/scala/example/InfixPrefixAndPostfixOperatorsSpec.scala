package example

import org.scalatest._

class InfixPrefixAndPostfixOperatorsSpec extends FlatSpec with Matchers {
  "Any method which takes a single parameter can be used as an infix operator" should "be" in {
    val g: Int = 3
    (g + 4) should be(7) // + is an infix operator
    g.+(4) should be(7)
  }
  "Infix operators do NOT work if an object has a method that takes two parameters" should "be" in {
    val g: String = "Check out the big brains on Brad!"
    g indexOf 'o' should be(6) //indexOf(Char) can be used as an infix operator
    // g indexOf 'o', 4 should be (6) //indexOf(Char, Int) cannot be used as an infix operator
    g.indexOf('o', 7) should be(25)
  }
  "Postfix operators have lower precedence than infix operators" should "be" in {
    val g: Int = 31
    (g toHexString) should be("1f")
  }
  "Prefix operators work if an object has a method name that starts with unary_" should "be" in {
    val g: Int = 31
    (-g) should be(-31)
  }
  "how to create a prefix operator. identifiers that can be used as prefix operators are +, -, !, and ~" should "be" in {
    class Stereo {
      def unary_+ = "on"

      def unary_- = "off"
    }

    val stereo = new Stereo
    (+stereo) should be("on")
    (-stereo) should be("off")
  }
}
