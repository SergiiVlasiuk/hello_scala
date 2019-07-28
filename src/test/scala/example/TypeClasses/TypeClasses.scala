package example.TypeClasses

import example.TypeClasses.Sorting.insertionSort
import org.scalatest.{FlatSpec, Matchers}

class TypeClasses extends FlatSpec with Matchers {

/*
  "LazyEvaluation" should "be" in {
    val compareRationals: (Rational, Rational) => Int =
    implicit val rationalOrder: Ordering[Rational] =
      (x: Rational, y: Rational) => compareRationals(x, y)
    val half = new Rational(1, 2)
    val third = new Rational(1, 3)
    val fourth = new Rational(1, 4)
    val rationals = List(third, half, fourth)
    insertionSort(rationals) shouldBe List(fourth, third, half)
  }
*/

  class Rational(x: Int, y: Int) {

    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    private val g = gcd(x, y)

    lazy val numer: Int = x / g
    lazy val denom: Int = y / g
  }
}
object Sorting {

  def insertionSort[A](xs: List[A])(implicit ord: Ordering[A]): List[A] = {
    def insert(y: A, ys: List[A]): List[A] =
      ys match {
        case List() => y :: List()
        case z :: zs =>
          if (ord.lt(y, z)) y :: z :: zs
          else z :: insert(y, zs)
      }

    xs match {
      case List() => List()
      case y :: ys => insert(y, insertionSort(ys))
    }
  }
}
