package example.week6

object OtherCollections extends App {
  val n = 7

  def isPrime(n: Int) = (2 until n) forall (n % _ != 0)

  private var tuples = (1 until n) flatMap (i => (1 until i) map (j => (i, j))) filter (
    pair => isPrime(pair._1 + pair._2)
  )

  println(tuples)

  tuples = for {
    i <- 1 until n // sequence
    j <- 1 until i // sequence
    if isPrime(i + j) // filter
  } yield (i, j)
  println(tuples)
  /*
  This for-loop is equivalent to:
  for (i <- 1 until n) {
      for (j <- 1 until i) {
          if(isPrime(i + j)) {
              yield (i, j)
          }
      }
  }
 */
}
