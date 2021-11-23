package arithmetic

import org.scalatest.FunSuite
import arithmetic.p31._

class p31Test extends FunSuite {

  test("testIsPrime") {
//    val actualPrimes: Seq[Int] = Stream.cons(2, Stream.from(3, 2) filter { _.isPrime }).take(5)
//    val actualPrimes: Seq[AnyVal] = Stream.cons(2, Stream.from(3, 2) map  { _.isPrime }) take(5)
//    val actualPrimes: Seq[AnyVal] = Stream.cons(2, Stream.from(3, 2)) map { x => { println(s"x: $x"); x}} map  { _.isPrime } take(5)
//    val actualPrimes: Seq[AnyVal] = Stream.from(3, 2) map  { _.isPrime } take(5)
    val actualPrimes: Seq[AnyVal] = Stream.from(2) map { x => { println(s"x: $x"); x}} map  { _.isPrime } take(10)

    assertResult(List(true, true, false, true, false, true, false, false, false, true))(actualPrimes)
  }

  test("test2IsPrime") {
//    val actualPrimes: Seq[Int] = Stream.cons(2, Stream.from(3, 2) filter { _.isPrime }).take(5)
    val actualPrimes: Seq[Boolean] = (2 to 11).map(_.isPrime).toList

    assertResult(List(true, true, false, true, false, true, false, false, false, true))(actualPrimes)
  }

}
