package example.standardLibrary

import org.scalatest.{FlatSpec, Matchers}

class FunctionalDataStructures extends FlatSpec with Matchers {
  "complex match expression" should "be ..." in {
    val x = List(1, 2, 3, 4, 5) match {
      case x :: 2 :: 4 :: xy     => x
      case Nil                   => 42
      case x :: y :: 3 :: 4 :: _ => x + y
      case h :: t                => h + 222
      case _                     => 101
    }
    x shouldBe 3
  }
  "Take a look at the implementation of List's tail function" should "be ..." in {
    def tail[A](l: List[A]): List[A] =
      l match {
        case Nil    => sys.error("tail of empty list")
        case _ :: t => t
      }

    tail(List(1, 2, 3)) shouldBe List(2, 3)
    tail(List(1)) shouldBe Nil
  }
  "setHead follows a similar principle" should "be ..." in {
    def setHead[A](l: List[A], h: A): List[A] = l match {
      case Nil    => sys.error("setHead on empty list")
      case _ :: t => h :: t
    }

    setHead(List(1, 2, 3), 3) shouldBe List(3, 2, 3)
    setHead(List("a", "b"), "c") shouldBe List("c", "b")
  }
  "We can generalize take to the function drop" should "be ..." in {
    def drop[A](l: List[A], n: Int): List[A] =
      if (n <= 0) l
      else
        l match {
          case Nil    => Nil
          case _ :: t => drop(t, n - 1)
        }

    drop(List(1, 2, 3), 1) shouldBe List(2, 3)
    drop(List(1, 2, 3), 0) shouldBe List(1, 2, 3)
    drop(List("a", "b"), 2) shouldBe Nil
    drop(List(1, 2), 3) shouldBe Nil
    drop(Nil, 1) shouldBe Nil
  }
  "dropWhile extends the behaviour of drop" should "be ..." in {
    def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
      l match {
        case h :: t if f(h) => dropWhile(t, f)
        case _              => l
      }

    dropWhile(List(1, 2, 3), (x: Int) => x < 2) shouldBe List(2, 3)
    dropWhile(List(1, 2, 3), (x: Int) => x > 2) shouldBe List(1, 2, 3)
    dropWhile(List(1, 2, 3), (x: Int) => x > 0) shouldBe Nil
    dropWhile(Nil, (x: Int) => x > 0) shouldBe Nil
  }
  "init can be implemented in the same fashion" should "but cannot be implemented in constant time like tail" in {
    def init[A](l: List[A]): List[A] =
      l match {
        case Nil      => sys.error("init of empty list")
        case _ :: Nil => Nil
        case h :: t   => h :: init(t)
      }

    init(List(1, 2, 3)) shouldBe List(1, 2)
    init(List(1)) shouldBe Nil
  }
  "Let's run through the steps that foldRight" should "will follow in our new implementation of sum2" in {
    List(1, 2, 3).foldRight(0)((x, y) => x + y) shouldBe 6
    1 + List(2, 3).foldRight(0)((x, y) => x + y) shouldBe 6
    2 + 1 + List(3).foldRight(0)((x, y) => x + y) shouldBe 6
    2 + 2 + 2 + List(0).foldRight(0)((x, y) => x + y) shouldBe 6
    2 + 2 + 1 + 1 shouldBe 6
  }
  "what happens when you pass Nil and Cons themselves to foldRight" should "be ..." in {
    List(1, 2, 3).foldRight(Nil: List[Int])(_ :: _) shouldBe List(1, 2, 3)
  }
  "use foldRight to calculate the length of a list" should "be ..." in {
    def l = List(1, 2, 3, 4, 5)

    def length[A](as: List[A]): Int = as.foldRight(0)((_, acc) => acc + 1)
    //    def length[A](as: List[A]): Int = as.length

    length(l) shouldBe 5
  }
  "write functions sum, product and length of a list using foldLeft" should "be ..." in {
    def sum3(l: List[Int]) = l.foldLeft(0)(_ + _)

    def product3(l: List[Double]) = l.foldLeft(1.0)(_ * _)

    def length2[A](l: List[A]): Int = l.foldLeft(0)((acc, h) => acc + 1)

    def listInts = List(1, 2, 3, 4, 5)

    def listDoubles = List(1.0, 2.0, 3.0)

    sum3(listInts) shouldBe 15
    product3(listDoubles) shouldBe 6.0
    length2(listInts) shouldBe 5
  }
  "Take a look at its implementation and check how it works" should "be ..." in {
    List(1, 2, 3) ::: List(1, 2) shouldBe List(1, 2, 3, 1, 2)
    List(1, 2, 3) ::: Nil shouldBe List(1, 2, 3)
    Nil ::: List(1, 2) shouldBe List(1, 2)
    Nil ::: Nil shouldBe Nil
  }
//  "function size to count the number of nodes (leaves and branches) in a tree" should "be ..." in {
//    def size[A](t: Tree[A]): Int = t match {
//      case Leaf(_) =>
//      case Branch(l, r) =>  + size(l) + size(r)
//    }
//
//    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
//    size(t) shouldBe 5
//  }
}
