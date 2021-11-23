package arithmetic

/*
import scala.annotation.tailrec

object p31 {

  implicit class IsPrime(val number: Int) {

    def isPrime: Boolean = {
      @tailrec
      def helper(currentN: Int, accumulator: Boolean): Boolean =
        if (currentN <= 1) accumulator
        else helper(currentN - 1, accumulator && number % currentN != 0)

      helper(number - 1, accumulator = true)
    }
  }
}
*/

object p31 {
  val primes = Stream.cons(2, Stream.from(3, 2) filter { _.isPrime })

  implicit class IsPrime(val start: Int) {

      def isPrime: Boolean =
      //    (start > 1) && (primes takeWhile { _ <= Math.sqrt(start) } forall { start % _ != 0 })
        (start > 1) && (primes takeWhile { _ <= Math.sqrt(start) } forall { start % _ != 0 })
  }
}
