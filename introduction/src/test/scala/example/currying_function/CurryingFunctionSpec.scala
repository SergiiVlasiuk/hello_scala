package example.currying_function

import org.scalatest.{FlatSpec, Matchers}

/**
  * @url("https://www.geeksforgeeks.org/currying-functions-in-scala-with-examples/")
  * Currying in Scala is simply a technique or a process of transforming a function. This function takes multiple arguments into a function that takes single argument. It is applied widely in multiple functional languages.
  *
  * Syntax
  *
  * def function name(argument1, argument2) = operation
  */
class CurryingFunctionSpec extends FlatSpec with Matchers {
  "Usual Function" should "add two numbers" in {
    // Define currying function
    def add(x: Int, y: Int): Int = x + y;

    add(20, 19) shouldBe 39
  }

  /**
    * Here, we have define add2 function which takes only one argument a and we are going to return a second function
    * which will have the value of add2. The second function will also take an argument let say b and this function
    * when called in main, takes two parenthesis(add2()()), where the first parenthesis is of the function add2 and
    * second parenthesis is of the second function. It will return the addition of two numbers, that is a+b.
    * Therefore, we have curried the add function, which means we have transformed the function that takes two
    * arguments into a function that takes one argument and the function itself returns the result.
    */
  "Currying Function" should "add two numbers" in {
    // transforming the function that
    // takes two(multiple) arguments into
    // a function that takes one(single) argument.
    def add2(a: Int): Int => Int = (b: Int) => a + b;

    add2(20)(19) shouldBe 39
  }
  it should "add two numbers with Partially Applied function" in {
    // transforming the function that
    // takes two(multiple) arguments into
    // a function that takes one(single) argument.
    def add2(a: Int): Int => Int = (b: Int) => a + b;

    add2(20)(19) shouldBe 39
    // Partially Applied function.
    val sum = add2(29)
    sum(5) shouldBe 34
  }
  it should "add two numbers with Partially Applied function v2" in {
    def add2: Int => Int => Int = (b: Int) => _ + b;

    add2(20)(19) shouldBe 39
    // Partially Applied function.
    val sum = add2(29)
    sum(5) shouldBe 34
  }
  it should "add two numbers with Partially Applied function v3" in {
    def add2: Int => Int => Int = (b: Int) => _ + b;

    add2(20)(19) shouldBe 39
    // Partially Applied function.
    val sum = add2(29)
    sum(5) shouldBe 34
  }
  it should "add two numbers with Partially Applied function in details" in {
    def add2(a: Int): Int => Int = {
      println(s"a: $a")
      b: Int =>
        {
          println(s"b: $b; (a+b): ($a + $b)")
          a + b
        }
    }

    add2(20)(19) shouldBe 39
    // Partially Applied function.
    val sum: Int => Int = add2(29)
    sum(5) shouldBe 34
  }
  it should "add two numbers with Partially Applied function in details2" in {
    def add2: Int=> Int => Int = {
      b: Int => _ + b
    }

    add2(20)(19) shouldBe 39
    // Partially Applied function.
    val sum: Int => Int = add2(29)
    println(s"sum: $sum")
    sum(5) shouldBe 34
  }

}
