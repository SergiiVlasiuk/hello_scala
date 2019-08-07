package example

import org.scalatest._

import scala.annotation.tailrec

class HelloSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    Hello.greeting shouldEqual "hello"
  }
  "16.toHexString" should "be 10" in {
    16.toHexString shouldEqual "10"
  }
  "(0 until 10).contains(10)" should "be false" in {
    (0 until 10).contains(10) shouldBe false
  }
  "\"foo\".drop(1)" should "be oo" in {
    "foo".drop(1) shouldEqual "oo"
  }
  "\"bar\".take(2)" should "be ba" in {
    "bar".take(2) shouldEqual "ba"
  }
  "\"Hello, Scala!\".toUpperCase" should "be \"HELLO, SCALA!\"" in {
    "Hello, Scala!".toUpperCase shouldEqual "HELLO, SCALA!"
  }
  "-42.abs" should "be 42" in {
    -42.abs shouldBe 42
  }
  "-42.abs" should "equal 42" in {
    -42.abs shouldEqual 42
  }
  "\"Hello, \" ++ \"Scala!\"" should "be \"Hello, Scala!\"" in {
    "Hello, " ++ "Scala!" shouldBe "Hello, Scala!"
  }
  "square(3.0)" should "be 9.0" in {
    def square(x: Double) = x * x
    square(3.0) shouldBe 9.0
  }
  "area(10)" should "be 314.159" in {
    def square(x: Double) = x * x
    def area(radius: Double): Double = 3.14159 * square(radius)
    area(10) shouldBe 314.159
  }
  "triangleArea(3, 4)" should "be 6.0" in {
    def triangleArea(base: Double, height: Double): Double = base * height / 2.0
    triangleArea(3, 4) shouldBe 6.0
  }
  "triangleArea(5, 6)" should "be 15.0" in {
    def triangleArea(base: Double, height: Double): Double = base * height / 2.0
    triangleArea(5, 6) shouldBe 15.0
  }
  "factorial(4)" should "be 24" in {
    def factorial(n: Int): Int =
      if (n == 0) 1
      else factorial(n - 1) * n
    factorial(4) shouldBe 24
  }
  "gcd(14, 21)" should "be 7" in {
    def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
    gcd(14, 21) shouldBe 7
  }
  "factorial(5)" should "be 120" in {
    def factorial(n: Int): Int = {
      @tailrec
      def iter(x: Int, result: Int): Int =
        if (x == 0) result
        else iter(x - 1, result * x)
      iter(n, 1)
    }
    factorial(5) shouldBe 120
  }
  "fractionOfWhole(Half)" should "be 0.5" in {
    sealed trait Duration
    case object Whole extends Duration
    case object Half extends Duration
    case object Quarter extends Duration

    def fractionOfWhole(duration: Duration): Double =
      duration match {
        case Whole => 1.0
        case Half => 0.5
        case Quarter => 0.25
      }
    fractionOfWhole(Half) shouldBe 0.5
  }
  "Some(\"I am wrapped in something\")" should "be Some(\"I am wrapped in something\")" in {
    val someValue: Option[String] = Some("I am wrapped in something")
    someValue shouldBe Some("I am wrapped in something")
    someValue shouldBe Option("I am wrapped in something")
  }
  "val emptyValue: Option[String] = None" should "be None" in {
    val emptyValue: Option[String] = None
    emptyValue shouldBe None
  }
  "maybeItWillReturnSomething(true)" should "be \"Found value\"" in {
    def maybeItWillReturnSomething(flag: Boolean): Option[String] = {
      if (flag) Some("Found value") else None
    }
    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)
    value1 getOrElse "No value" shouldBe "Found value"
    value2 getOrElse "No value" shouldBe "No value"
    value2 getOrElse {
      "default function"
    } shouldBe "default function"
    value1.isEmpty should be(false)
    value2.isEmpty should be(true)
  }
  "Option[Double] = Some(20.0)" should "be 20.0" in {
    val someValue: Option[Double] = Some(20.0)
    val value = someValue match {
      case Some(v) ⇒ v
      case None ⇒ 0.0
    }
    value should be(20.0)
    val noValue: Option[Double] = None
    val value1 = noValue match {
      case Some(v) ⇒ v
      case None ⇒ 0.0
    }
    value1 should be(0.0)
  }
  "Option map" should "be Some(4.5)" in {
    val number: Option[Int] = Some(3)
    val noNumber: Option[Int] = None
    val result1 = number.map(_ * 1.5)
    val result2 = noNumber.map(_ * 1.5)
    result1 should be(Some(4.5))
    result2 should be(None)
  }
  "Option fold(1)" should "be 9" in {
    val number: Option[Int] = Some(3)
    val noNumber: Option[Int] = None
    val result1 = number.fold(1)(_ * 3)
    val result2 = noNumber.fold(1)(_ * 3)
    result1 should be(9)
    result2 should be(1)
  }

}
