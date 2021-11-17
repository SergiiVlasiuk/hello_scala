package example.partial_function

object PartialFunctionOtherExample {

  val divide: PartialFunction[Int, Int] =
    new PartialFunction[Int, Int] {
      def apply(x: Int): Int =
        42 / x
      def isDefinedAt(x: Int): Boolean =
        x != 0
    }

  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 =>
      x + " is even"
  }

  val isOdd: PartialFunction[Int, String] = {
    case x if x % 2 == 1 =>
      x + " is odd"
  }

  def main(args: Array[String]): Unit = {
    println("===")
    val sample = 1 to 10

    // the method collect can use isDefinedAt to select which members to collect
    val evenNumbers = sample collect isEven
    println(s"evenNumbers: $evenNumbers")

    val isEvenOrOdd: PartialFunction[Int, String] = isEven orElse isOdd
    // the method orElse allows chaining another partial function to handle
    // input outside the declared domain
    val numbers = sample map isEvenOrOdd
    println(s"numbers: $numbers")

    println("===")
    println(s"divide2: ${divide(2)}")
    println(s"divide1: ${divide(1)}")
    println(s"divide0: ${divide lift 0}")
    println(s"divide0: ${divide(0)}")
  }
}
