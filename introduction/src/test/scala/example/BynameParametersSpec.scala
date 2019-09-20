package example

import org.scalatest._

class BynameParametersSpec extends FlatSpec with Matchers {
  "() => Int is a Function type that takes a Unit type. Unit is known as void" should "be" in {
    def calc(x: () ⇒ Int): Either[Throwable, Int] = {
      try {
        Right(x()) //An explicit call of the x function
      } catch {
        case b: Throwable ⇒ Left(b)
      }
    }

    val y = calc { () ⇒ //Having explicitly declaring that Unit is a parameter with ()
      14 + 15
    }
    y should be(Right(29))
  }
  "by-name parameter does the same thing as the previous koan but there is no need to explicitly handle Unit or ()" should "be" in {
    def calc(x: ⇒ Int): Either[Throwable, Int] = {
      //x is a call by name parameter
      try {
        Right(x)
      } catch {
        case b: Throwable ⇒ Left(b)
      }
    }

    val y = calc {
      //This looks like a natural block
      println("Here we go!") //Some superfluous call
      val z = List(1, 2, 3, 4) //Another superfluous call
      49 + 20
    }
    y should be(Right(69))
  }
  "By name parameters can also be used with object and apply to make interesting block-like calls" should "be" in {
    object PigLatinizer {
      def apply(x: ⇒ String) = x.tail + x.head + "ay"
    }
    val result = PigLatinizer {
      val x = "pret"
      val z = "zel"
      x ++ z //concatenate the strings
    }
    result should be("retzelpay")
  }
}
