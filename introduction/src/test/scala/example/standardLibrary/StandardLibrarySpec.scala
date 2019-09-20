package example.standardLibrary

import org.scalatest._

class StandardLibrarySpec extends FlatSpec with Matchers {
  "Complete the definition insertion sort by filling in the blanks in the definition below" should "be" in {
    val cond: (Int, Int) => Boolean = _ < _

    def insert(x: Int, xs: List[Int]): List[Int] =
      xs match {
        case List()  => x :: Nil
        case y :: ys =>
//          if (cond(x, y)) x :: y :: ys
          if (cond(x, y)) x :: xs
          else y :: insert(x, ys)
      }

    insert(2, 1 :: 3 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
    insert(1, 2 :: 3 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
    insert(3, 1 :: 2 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
  }
  "Transform an optional value with map" should "be" in {
    Some(1).map(x => x + 1) shouldBe Some(2)
    None.map((x: Int) => x + 1) shouldBe None
    Some(10).map(x => x + 1) shouldBe Some(11)
  }
  "Filter values with filter" should "be" in {
    Some(1).filter(x => x % 2 == 0) shouldBe None
    Some(2).filter(x => x % 2 == 0) shouldBe Some(2)
    Some(21).filter(x => x % 2 == 0) shouldBe None
  }
  "Use flatMap to transform a successful value into an optional value" should "be" in {
    Some(1).flatMap(x => Some(x + 1)) shouldBe Some(2)
    None.flatMap((x: Int) => Some(x + 1)) shouldBe None
    Some(21).flatMap(x => Some(x + 1)) shouldBe Some(22)
  }
  "Scala 2.12, Either was “unbiased”. You had to explicitly specify which “side” (Left or Right) you wanted to map" should "be" in {
    def triple(x: Int): Int = 3 * x

    def tripleEither(x: Either[String, Int]): Either[String, Int] =
      x.right.map(triple)

    tripleEither(Right(1)) shouldBe Right(3)
    tripleEither(Left("not a number")) shouldBe Left("not a number")
  }
}
