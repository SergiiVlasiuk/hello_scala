package example.partial_function

object PartialFunctionsTraining {
  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }
  val isOdd: PartialFunction[Int, String] = {
    case x if x % 2 == 1 => x + " is odd"
  }
  val one: PartialFunction[Int, String] = { case 1      => "one" }
  val two: PartialFunction[Int, String] = { case 2      => "two" }
  val three: PartialFunction[Int, String] = { case 3    => "three" }
  val four: PartialFunction[Int, String] = { case 4    => "four" }
  val wildcard: PartialFunction[Int, String] = { case x => s"something else: $x" }

  val partial
    : PartialFunction[Int, String] = one orElse two orElse three orElse four orElse wildcard
  val oneEvenOdd: PartialFunction[Int, String] = one orElse isEven orElse isOdd

  def square(x:Int) = x * x
  val partialOfSquare = partial compose square _
  val partialOfSquare2 = square _ andThen partial

  def main(args: Array[String]): Unit = {
    Some(3).map(oneEvenOdd).foreach(println)
    Some(2).map(oneEvenOdd).foreach(println)
    Some(1).map(oneEvenOdd).foreach(println)
    println(s"one.isDefinedAt(1): ${one.isDefinedAt(1)}")
    println(s"one.isDefinedAt(2): ${one.isDefinedAt(2)}")
    println(s"one.isDefinedAt(4): ${one.isDefinedAt(4)}")

    println(s"partial.isDefinedAt(1): ${partial.isDefinedAt(1)}")
    println(s"partial.isDefinedAt(4): ${partial.isDefinedAt(4)}")
    println(s"partial.isDefinedAt(6): ${partial.isDefinedAt(6)}")
    println(s"partialOfSquare(2): ${partialOfSquare(2)}")
    println(s"partialOfSquare2(2): ${partialOfSquare2(2)}")
  }
}
