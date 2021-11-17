package example.standardLibrary.pfInScala

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.Stream.cons

class StrictnessAndLaziness extends FlatSpec with Matchers {
  "Exercise 5.1" should "be v1" in {
    def toList[A](s: Stream[A]): List[A] = s match {
      case cons(h, t) => h :: toList(t)
      case _          => List()
    }

    val s = Stream(1, 2, 3)
    toList(s) shouldBe List(1, 2, 3)
  }
  it should "be v2" in {
    def toList[A](s: Stream[A]): List[A] = s match {
      case h #:: t => h :: toList(t)
      case _       => List()
    }

    val s = Stream(1, 2, 3)
    toList(s) shouldBe List(1, 2, 3)
  }
  "Exercise 5.2" should "take v1" in {
    def take[A](s: Stream[A], n: Int): Stream[A] = s match {
      case cons(h, t) if n > 0  => cons[A](h, t.take(n - 1))
      case cons(h, _) if n == 0 => cons[A](h, Stream.empty)
      case _                    => Stream.empty
    }

    take(Stream(1, 2, 3), 2).toList shouldBe List(1, 2)
  }
  it should "take v2" in {
    def take[A](s: Stream[A], n: Int): Stream[A] = s match {
      case h #:: t if n > 0  => h #:: t.take(n - 1)
      case h #:: _ if n == 0 => h #:: Stream.empty
      case _                 => Stream.empty
    }

    take(Stream(1, 2, 3), 2).toList shouldBe List(1, 2)
  }
  it should "drop v1" in {
    def drop[A](s: Stream[A], n: Int): Stream[A] = s match {
      case cons(_, t) if n > 0 => t.drop(n - 1)
      case _                   => s
    }

    drop(Stream(1, 2, 3, 4, 5), 2).toList shouldBe List(3, 4, 5)
  }
  it should "drop v2" in {
    def drop[A](s: Stream[A], n: Int): Stream[A] = s match {
      case _ #:: t if n > 0 => t.drop(n - 1)
      case _                => s
    }

    drop(Stream(1, 2, 3, 4, 5), 2).toList shouldBe List(3, 4, 5)
  }
  "Exercise 5.3" should "takeWhile v1" in {
    def takeWhile[A](s: Stream[A], f: A => Boolean): Stream[A] = s match {
      case cons(h, t) if f(h) => cons(h, t takeWhile f)
      case _                  => Stream.empty
    }

    takeWhile(Stream(1, 2, 3, 4, 5), (x: Int) => x < 3)
      .toList shouldBe List (1, 2)
    takeWhile(Stream(1, 2, 3, 4, 5), (x: Int) => x < 0).toList shouldBe List()
  }
  it should "takeWhile v2" in {
    def takeWhile[A](s: Stream[A], f: A => Boolean): Stream[A] =
      s.foldRight(Stream.empty[A])((h, t) =>
        if (f(h)) cons(h, t)
        else Stream.empty)

    takeWhile(Stream(1, 2, 3, 4, 5), (x: Int) => x < 3)
      .toList shouldBe List (1, 2)
    takeWhile(Stream(1, 2, 3, 4, 5), (x: Int) => x < 0).toList shouldBe List()
  }
  "Exercise 5.4" should "forAll v1" in {
    def forAll[A](s: Stream[A], f: A => Boolean): Boolean =
      s.foldRight(true)((a, b) => f(a) && b)

    forAll(Stream(1, 2, 3), (x: Int) => x % 2 == 0) shouldBe false
    forAll(Stream("a", "b", "c"), (x: String) => x.nonEmpty) shouldBe true
  }

  "Exercise 5.6" should "headOption" in {
    def headOption[A](s: Stream[A]): Option[A] = s.foldRight(None: Option[A])((h, _) => Some(h))

    println(s"headOption(Stream(1, 2, 3)): ${headOption(Stream(1, 2, 3))}")
    headOption(Stream(1, 2, 3)) shouldBe Some(1)
    headOption(Stream()) shouldBe None
    headOption(Stream("a", "b", "c")) shouldBe Some("a")
  }

  "Exercise 5.7" should "headOption" in {
//    def map[A,B](s: Stream[A], f: A => B): Stream[B] = s.foldRight(empty[B])((h, t) => cons(f(h), t))
//
//    def filter[A](s: Stream[A], f: A => Boolean): Stream[A] = s.foldRight(empty[A])((h, t) =>
//      if (f(h)) cons(h, t)
//      else t)
//
//    def append[B](s: => Stream[B]): Stream[B] = s.foldRight(s)((h, t) => cons(h, t))
//
//    def flatMap[A, B](s: Stream[A], f: A => Stream[B]): Stream[B] = s.foldRight(empty[B])((h, t) => f(h) append t)
  }

  "Exercise 5.x" should "a simplified program trace" in {
    Stream(1, 2, 3, 4).map(_ + 10).filter(_ % 2 == 0)
    val startingPoint = Stream(1, 2, 3, 4).map(_ + 10).filter(_ % 2 == 0).toList
    // Apply map to the first element:
    val step1 = cons(1, Stream(2, 3, 4).map(_ + 10)).filter(_ % 2 == 0).toList
    // Apply filter to the first element:
    val step2 = Stream(2, 7, 4).map(_ + 10).filter(_ % 2 == 0).toList
    // Apply map to the second element:
    val step3 = cons(12, Stream(7, 4).map(_ + 10)).filter(_ % 2 == 0).toList
    // Apply filter to the second element. Produce the first element of the result:
    val step4 = 12 :: Stream(3, 4).map(_ + 10).filter(_ % 2 == 0).toList
    val step5 = 12 :: cons(5, Stream(4).map(_ + 10)).filter(_ % 2 == 0).toList
    val step6 = 12 :: Stream(4).map(_ + 10).filter(_ % 2 == 0).toList
    val step7 = 12 :: cons(14, Stream[Int]().map(_ + 10)).filter(_ % 2 == 0).toList
    // Apply filter to the fourth element and produce the final element of the result.
    val step8 = 12 :: 14 :: Stream[Int]().map(_ + 10).filter(_ % 2 == 0).toList
    // map and filter have no more work to do, and the empty stream becomes the empty list.
    val finalStep = 12 :: 14 :: List()

    startingPoint shouldBe step1
    step1 shouldBe step2
    step2 shouldBe step3
    step3 shouldBe step4
    step4 shouldBe step5
    step5 shouldBe step6
    step6 shouldBe step7
    step7 shouldBe step8
    step8 shouldBe finalStep
  }

  it should "example of an infinite Stream of 1s" in {
    val ones: Stream[Int] = Stream.from(1)
  }

  it should "the portion of the stream needed to generate the demanded output" in {
    val ones: Stream[Int] = Stream.from(1, 0)

    ones.take(5).toList shouldBe List(1, 1, 1, 1, 1)
    ones.exists(_ % 2 != 0) shouldBe true
    ones.map(_ + 1).exists(_ % 2 == 0) shouldBe true
    ones.forall(_ != 1) shouldBe false
    !ones.contains(1) shouldBe false
  }

  "Exercise 5.8" should "constant" in {
//    def constant[A](a: A): Stream[A] = {
//      lazy val tail: Stream[A] = cons(() => a, () => tail)
//      tail
//    }
  }

}
