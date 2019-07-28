package example.SyntacticConveniences

import org.scalatest._

class SyntacticConveniencesSpec extends FlatSpec with Matchers {
  "To splice values into constant String at runtime, you can use string interpolation" should "be" in {
    def greet(name: String): String =
      s"Hello, $name!"
    greet("Scala") shouldBe "Hello, Scala!"
    greet("Functional Programming") shouldBe "Hello, Functional Programming!"
  }
  "If you want to splice a complex expression (more than just an identifier), surround it with braces" should "be" in {
    def greet(name: String): String =
      s"Hello, ${name.toUpperCase}!"
    greet("Scala") shouldBe "Hello, SCALA!"
  }
  "to aggregate information without having to define a complete case class for it. In such a case you can use tuples" should "be" in {
    def pair(i: Int, s: String): (Int, String) = (i, s)

    pair(42, "foo") shouldBe (42, "foo")
    pair(0, "bar") shouldBe pair(0, "bar")
    pair(0, "bar") shouldBe (0, "bar")
  }
  "You can retrieve the elements of a tuple by using a tuple pattern" should "be" in {
    val is: (Int, String) = (42, "foo")

    is match {
      case (i, s) =>
        i shouldBe 42
        s shouldBe "foo"
    }
    val (i, s) = is
    i shouldBe 42
    s shouldBe "foo"
  }
  "Alternatively, you can retrieve the 1st element with the _1 member, the 2nd element with the _2 member" should "be" in {
    val is: (Int, String) = (42, "foo")
    is._1 shouldBe 42
    is._2 shouldBe "foo"
  }
  "the step parameter has a default value, 1" should "be" in {
    case class Range(start: Int, end: Int, step: Int = 1)
    val xs = Range(start = 1, end = 10)
    xs.step shouldBe 1
  }
  "You can define a function that can receive an arbitrary number of parameters (of the same type) as follows" should "be" in {
    def average(x: Int, xs: Int*): Double =
      (x :: xs.to[List]).sum.toDouble / (xs.size + 1)

    average(1) shouldBe 1.0
    average(1, 2) shouldBe 1.5
    average(1, 2, 3) shouldBe 2.0
  }
  "you can give meaningful names to type expressions" should "be" in {
    type Result = Either[String, (Int, Int)]
    def divide(dividend: Int, divisor: Int): Result =
      if (divisor == 0) Left("Division by zero")
      else Right((dividend / divisor, dividend % divisor))
    divide(6, 4) shouldBe Right((1, 2))
    divide(2, 0) shouldBe Left("Division by zero")
    divide(8, 4) shouldBe Right(2, 0)
  }
}
