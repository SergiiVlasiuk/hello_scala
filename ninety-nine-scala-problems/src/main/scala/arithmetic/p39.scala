package arithmetic

import scala.language.postfixOps
import arithmetic.p31._

import scala.collection.immutable.Range.Inclusive
// P39 (*) A list of prime numbers.
//     Given a range of integers by its lower and upper limit, construct a list
//     of all prime numbers in that range.
//
//     scala> listPrimesinRange(7 to 31)
//     res0: List[Int] = List(7, 11, 13, 17, 19, 23, 29, 31)

object p39 {
  def listPrimesInRange(r: Range): List[Int] =
//    primes dropWhile { _ < r.first } takeWhile { _ <= r.last } toList
    primes dropWhile { _ < r.start } takeWhile { _ <= r.last } toList

  def listPrimesInRange2(range: Inclusive): List[Int] =
    range.filter(_.isPrime).toList
}
