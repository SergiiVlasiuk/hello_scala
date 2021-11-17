package example.partial_functions

import org.scalatest.{FlatSpec, Matchers}

/*
https://www.geeksforgeeks.org/partial-functions-in-scala/
 */
class PartialFunctionsSpec extends FlatSpec with Matchers {
  "Partial function" should "using two methods " in {
    // Creating Partial function using two methods
    val r = new PartialFunction[Int, Int] {
      // Applying isDefinedAt method
      def isDefinedAt(q: Int) = q != 0
      // Applying apply method
      def apply(q: Int) = 12 * q
    }
    r(10) shouldBe 120
    r(1) shouldBe 12
    r(0) shouldBe 0
  }

  it should "using case statements" in {
    val d: PartialFunction[Int, Int] = {
      // using case statement
      case x if (x % 3) == 0 => x * 3
      case x                 => x
    }
    d(3) shouldBe 9
    d(4) shouldBe 4
    d(5) shouldBe 5
  }

  it should "using orElse " in {
    // Creating Partial function1
    val M: PartialFunction[Int, Int] = {
      // using case statement
      case x if (x % 5) == 0 => x * 5
    }

    // Creating Partial function2
    val m: PartialFunction[Int, Int] = {
      // using case statement
      case y if (y % 2) == 0 => y * 2
    }

    // chaining two partial
    // functions using orElse
    val r = M orElse m

    r(5) shouldBe 25
    r(4) shouldBe 8
  }

  it should "using collect method " in {
    // Creating Partial function
    val M: PartialFunction[Int, Int] = {
      // using case statement
      case x if (x % 5) != 0 => x * 5
    }

    // Applying collect method
    val y = List(7, 15, 9) collect M

    y shouldBe List(35, 45)
  }

  it should "using andThen method " in {
    // Creating Partial function
    val M: PartialFunction[Int, Int] = {
      // using case statement
      case x if (x % 4) != 0 => x + 4
    }

    // Creating another function
    val append = (x: Int) => x * 10

    // Applying andThen method
    val y = M andThen append

    y(7) shouldBe 110
  }
}
