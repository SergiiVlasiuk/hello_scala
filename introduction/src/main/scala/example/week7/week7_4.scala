package example.week7

import example.week7.primes.{m4s, primeNumbers}

object week7_4 extends App {

  /** Back to square roots */
  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  } //> sqrtStream: (x: Double)Stream[Double]

  println(sqrtStream(2).take(10).toList)
  /* we can add the termination criteria as before */
  def isGoodEnough(guess: Double, x: Double) =
    math.abs((guess * guess - x) / x) < 0.0001 //> isGoodEnough: (guess: Double, x: Double)Boolean

  println(sqrtStream(4).take(10).toList)
  println(sqrtStream(4).filter(isGoodEnough(_, 4)).take(10).toList)
  //> res5: List[Double] = List(2.0000000929222947, 2.000000000000002, 2.0, 2.0,
  //| 2.0, 2.0, 2.0, 2.0, 2.0, 2.0)

  println("scala" drop 1) // > res_: String = cala

  println(m4s.take(1).toList)
//  println(m4s.take(10).toList)
//  println(m4s.take(100).toList)

  println(primeNumbers.take(1).toList)
  println(primeNumbers.take(10).toList)
  println(primeNumbers.take(100).toList)
}

object primes {
  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  val nats = from(0)
  val m4s = nats map (_ * 4)

  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))

  val primeNumbers = sieve(from(2))
}
