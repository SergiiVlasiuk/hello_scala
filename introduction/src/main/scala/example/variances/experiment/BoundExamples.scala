package example.variances.experiment

/*
Bounds (with): T <: X, T >: X
View bounds: T <% X - can be converted to X
Content bounds(multiple): T : X - requires implicit value X[T]
  implicitly
Type constraints: T =:= X, T <:< X, T => X
* */
object BoundExamples {
  def main(args: Array[String]): Unit = {
    val w = new Wrapper[String]("hi bound")
    println(w)
    println(w.map(_.length))

    println("/=== max results ===/")
    val five = Wrapper(5)
    println(five.max(4))
    println(five.max(5))
    println(five.max(6))
  }
}

//case class Wrapper[T <% Comparator[T]](x: T){
//  def map[R <% Comparator[R]](fn: T => R): Wrapper[R] = Wrapper(fn(x))
//}
//case class Wrapper[T <: Comparator[T]](x: T){
//  def map[R <: Comparator[R]](fn: T => R): Wrapper[R] = Wrapper(fn(x))
//}
// ordering like a comparator
//case class Wrapper[T](x: T)(implicit ordering: Ordering[T]){
//  def map[R](fn: T => R): Wrapper[R] = Wrapper(fn(x))
//}

// next records are identical (in the second case context bound used)
//case class Wrapper[T](x: T)(implicit ordering: Ordering[T])
//case class Wrapper[T : Ordering](x: T)

case class Wrapper[T : Ordering](x: T){
  def map[R : Ordering](fn: T => R): Wrapper[R] = Wrapper(fn(x))

  def max[R >: T : Ordering](maybeMax: R): Wrapper[R] = {
    val ordering: Ordering[R] = implicitly[Ordering[R]]
//    if (ordering.compare(x, maybeMax) >= 0) this.map(_.asInstanceOf[R]) // huck,
    if (ordering.compare(x, maybeMax) >= 0) this.map(_.asInstanceOf[R])
    else Wrapper(maybeMax)
  }
}

/*
T <: X - обмежений згори

*/

