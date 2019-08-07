package example.week7

object week7_2 extends App {
  println("scala" drop 1) // > res_: String = cala

  println("difference between stream range and list range:")
  println("pay attantion on return type, null (Nil/Empty) values,  consequence (cons / ::)")
  println("stream range: streamRange(1, ?)")
  println("List range: listRange(1, 10)")

  def streamRange(lo: Int, hi: Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    //    else Stream.cons(lo, streamRange(lo + 1, hi))
    else lo #:: streamRange(lo + 1, hi) // similar instruction to `Stream.cons`

  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)

  def expr = {
    val x = {
      print("x")
      1
    }
    lazy val y = {
      print("y")
      2
    }

    def z = {
      print("z")
      3
    }

    z + y + x + z + y + x
  }

  expr
}

