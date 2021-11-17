package example.variances.experiment

object GenericExample {
  def main(args: Array[String]): Unit = {
    val w = new Wrapper[String]("hi generic")
    println(w)
  }

  case class Wrapper[T](x: T){
    def map[R](fn: T => R): Wrapper[R] = Wrapper(fn(x))
  }

}
