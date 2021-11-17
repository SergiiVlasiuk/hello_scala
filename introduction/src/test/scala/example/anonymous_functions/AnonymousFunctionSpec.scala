package example.anonymous_functions

import org.scalatest.{FlatSpec, Matchers}

/**
  * Syntax:
  *
  * (z:Int, y:Int)=> z*y
  * Or
  * (_:Int)*(_Int)
  */
class AnonymousFunctionSpec extends FlatSpec with Matchers {
  "Anonymous Function" should "with multiple parameters assigning to to variables" in {
    // Creating anonymous functions
    // with multiple parameters Assign
    // anonymous functions to variables
    var myfc1 = (str1: String, str2: String) => str1 + str2
    println(myfc1("Geeks", "12Geeks"))
    myfc1("Geeks", "12Geeks") shouldBe "Geeks12Geeks"
  }

  it should "using _ wildcard instead of variable name" in {
    // An anonymous function is created
    // using _ wildcard instead of
    // variable name because str1 and
    // str2 variable appear only once
    var myfc2 = (_: String) + (_: String)

    // Here, the variable invoke like a function call
    println(myfc2("Geeks", "forGeeks"))
    myfc2("Geeks", "forGeeks") shouldBe "GeeksforGeeks"
  }

  it should "without parameter" in {
    // Creating anonymous functions
    // without parameter
    var myfun1 = () => { "Welcome to GeeksforGeeks...!!" }
    println(myfun1())

    // A function which contain anonymous
    // function as a parameter
    def myfunction(fun: (String, String) => String) = {
      fun("Dog", "Cat")
    }

    // Explicit type declaration of anonymous
    // function in another function
    val f1: String = myfunction((str1: String, str2: String) => str1 + str2)
    println(f1)
    f1 shouldBe "DogCat"
  }

  it should "Explicit type declaration of anonymous function in another function " in {
    // A function which contain anonymous
    // function as a parameter
    def myfunction(fun: (String, String) => String) = fun("Dog", "Cat")
    // Shorthand declaration using wildcard
    val f2: String = myfunction(_ + _)
    println(f2)
    f2 shouldBe "DogCat"
  }
}
