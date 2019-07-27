package example

import org.scalatest._

class FormattingSpec extends FlatSpec with Matchers {
  "String can be placed in format" should "be" in {
    val s = "Hello World"
    "Application %s".format(s) should be("Application Hello World")
  }
  "Character Literals can be a single character" should "be" in {
    val a = 'a'
    val b = 'B'

    //format(a) is a string format, meaning the "%c".format(x)
    //will return the string representation of the char.

    "%c".format(a) should be("a")
    "%c".format(b) should be("B")
  }
  "Character Literals can be an escape sequence, including octal or hexidecimal" should "be" in {
    val c = 'a' //unicode for a
    val d = '\141' //octal for a
    val e = '\"'
    val f = '\\'

    "%c".format(c) should be("a")
    "%c".format(d) should be("a")
    "%c".format(e) should be("\"")
    "%c".format(f) should be("\\")
  }
  "Formatting can also include numbers" should "be" in {
    val j = 190
    "%d bottles of beer on the wall" format j - 100 should be("90 bottles of beer on the wall")
  }
  "Formatting can be used for any number of items, like a string and a number" should "be" in {
    val j = 190
    val k = "vodka"
    "%d bottles of %s on the wall".format(j - 100, k) should be("90 bottles of vodka on the wall")
  }
}
