package example.standardLibrary

import org.scalatest.{FlatSpec, Matchers}

/**
  * https://www.scala-exercises.org/fp_in_scala/functional_data_structures
  */
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
  "Exercise 3.1" should "Examine the next complex match expression" in {
    val x = List(1, 2, 3, 4, 5) match {
      case x +: 2 +: 4 +: _ => x
      case Nil => 42
      case x +: y +: 3 +: 4 +: _ => x + y
      case h +: t => h + t.sum
      case _ => 101
    }
    x shouldBe 3
  }
  "Exercise 3.2" should "tail function" in {
    def tail[A](l: List[A]): List[A] =
      l match {
        case Nil => Nil
        case _ +: t => t
      }

    tail(List(1, 2, 3)) shouldBe List(2, 3)
    tail(List(1)) shouldBe Nil
    tail(List()) shouldBe Nil
  }
  "Exercise 3.3" should "set head" in {
    def setHead[A](l: List[A], h: A): List[A] = l match {
      case Nil => sys.error("setHead on empty list")
      case _ +: t => h +: t
    }

    setHead(List(1, 2, 3), 3) shouldBe List(3, 2, 3)
    setHead(List("a", "b"), "c") shouldBe List("c", "b")
  }
  it should "add head" in {
    def setHead[A](l: List[A], h: A): List[A] = h+:l

    setHead(List(1, 2, 3), 3) shouldBe List(3, 1, 2, 3)
    setHead(List("a", "b"), "c") shouldBe List("c", "a", "b")
  }
  "Exercise 3.4" should "take to the function drop" in {
    def drop[A](l: List[A], n: Int): List[A] =
      if (n <= 0) l
      else
        l match {
          case Nil => Nil
          case _ +: t => drop(t, n - 1)
        }

    drop(List(1, 2, 3), 1) shouldBe List(2, 3)
    drop(List(1, 2, 3), 0) shouldBe List(1, 2, 3)
    drop(List("a", "b"), 2) shouldBe Nil
    drop(List(1, 2), 3) shouldBe Nil
    drop(Nil, 1) shouldBe Nil
  }
  "Exercise 3.5" should "dropWhile" in {
    def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
      l match {
        case h +: t if f(h) => dropWhile(t, f)
        case _ => l
      }

    dropWhile(List(1, 2, 3), (x: Int) => x < 2) shouldBe List(2, 3)
    dropWhile(List(1, 2, 3), (x: Int) => x > 2) shouldBe List(1, 2, 3)
    dropWhile(List(1, 2, 3), (x: Int) => x > 0) shouldBe Nil
    dropWhile(Nil, (x: Int) => x > 0) shouldBe Nil
  }
  "Exercise 3.6" should "init" in {
    def init[A](l: List[A]): List[A] =
      l match {
        case Nil => sys.error("init of empty list")
        case _ +: Nil => Nil
        case h +: t => h +: init(t)
      }

    init(List(1, 2, 3)) shouldBe List(1, 2)
    init(List(1)) shouldBe Nil
  }
  "Exercise 3.7" should "foldRight for sum" in {
    (1 +: 2 +: 3 +: Nil).foldRight(0)((value, acc) => value + acc) shouldBe 6
    1 + (2 +: 3 +: Nil).foldRight(0)((value, acc) => value + acc) shouldBe 6
    1 + 2 + (3 +: Nil).foldRight(0)((value, acc) => value + acc) shouldBe 6
    1 + 2 + +3 + List[Int]().foldRight(0)((value, acc) => value + acc) shouldBe 6
  }
  "Exercise 3.8" should "foldRight for concat" in {
    List(1, 2, 3).foldRight(List[Int]())((value, acc) => value +: acc) shouldBe List(1, 2, 3)
  }
  "Exercise 3.9" should "foldRight to calculate the length of a list" in {
    def l = List(1, 2, 3, 4, 5)

    def length[A](as: List[A]): Int = as.foldRight(0)((_, acc) => acc + 1)

    length(l) shouldBe 5
  }
  "Exercise 3.10 - 3.11" should "foldLeft - another general tail-recursive list-recursion function" in {
    def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B =
      l match {
        case Nil => z
        case h +: t => t.foldLeft(f(z, h))(f)
      }
    def sum3(l: List[Int]) = foldLeft(l, 0)(_ + _)
    def product3(l: List[Double]) = foldLeft(l, 1.0)(_ * _)
    def length2[A](l: List[A]): Int = foldLeft(l, 0)((acc, h) => acc + 1)
    def listInts = List(1, 2, 3, 4, 5)
    def listDoubles = List(1.0, 2.0, 3.0)

    sum3(listInts) shouldBe 15
    product3(listDoubles) shouldBe 6.0
    length2(listInts) shouldBe 5
  }
  "Exercise 3.12 - 3.15" should "appended using foldRight" in {
    def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B =
      l match {
        case Nil => z
        case h +: t => t.foldLeft(f(z, h))(f)
      }
    def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((acc, h) => h +: acc)
    def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B =
      foldLeft(reverse(l), z)((b,a) => f(a,b))
    def foldLeftViaFoldRight[A,B](l: List[A], z: B)(f: (B,A) => B): B =
      foldRightViaFoldLeft(l, (b:B) => b)((a,g) => b => g(f(b,a)))(z)
    def appendViaFoldRight[A](l: List[A], r: List[A]): List[A] =
      foldRightViaFoldLeft(l, r)(_ +: _)

    appendViaFoldRight(List(1, 2, 3), List(1, 2)) shouldBe List(1, 2, 3, 1, 2)
    List(1, 2, 3).appendedAll(List(1, 2)) shouldBe List(1, 2, 3, 1, 2)
    List(1, 2, 3).appendedAll(Nil) shouldBe List(1, 2, 3)
    Nil.appendedAll(List(1, 2)) shouldBe List(1, 2)
    Nil.appendedAll(Nil) shouldBe Nil

    // concat function using foldRight
    def concat[A](l: List[List[A]]): List[A] =
      l.foldRight(Nil: List[A])(appendViaFoldRight)
    def concat2[A](l: List[List[A]]): List[A] =
      l.foldRight(Nil: List[A])((value, acc) => acc.appendedAll(value))
  }

  "Exercise 3.16" should "function that transforms a list of integers by adding 1 to each element" in {
    def add1(l: List[Int]): List[Int] = l.foldRight(Nil: List[Int])((h, t) => (h + 1) +: t)
    add1(List(1, 2, 3)) shouldBe List(2, 3, 4)
  }

  "Exercise 3.17" should "function that turns each value in a List[Double] into a String" in {
    def doubleToString(l: List[Double]): List[String] =
      l.foldRight(Nil: List[String])((h, t) => h.toString +: t)
  }

  "Exercise 3.18" should "generalize Exercise 3.16 - 3.17" in {
    def map[A, B](l: List[A])(f: A => B): List[B] =
      l.foldRight(Nil: List[B])((h, t) => f(h) +: t)
  }

  "Exercise 3.19" should "remove all odd numbers from a List[Int]" in {
    def removeOdds(l: List[Int]): List[Int] =
      l.foldRight(Nil: List[Int])((h, t) => if (h % 2 == 0) h +: t else t)
    removeOdds(List(1, 2, 3, 4, 5)) shouldBe List(2, 4)
  }

  "Exercise 3.20.v1" should "concat lists - v1" in {
    def map[A](l: List[A])(f: A => List[A]): List[A] =
      l.foldRight(Nil: List[A])((h, t) => f(h) appendedAll t)
//      l.foldRight(Nil: List[A])((h, t) => f(h) :: t)
    def flatMap[A](l: List[A])(f: A => List[A]): List[A] =
      map(l)(f)
    flatMap(List(1, 2, 3))(i => List(i, i)) shouldBe List(1, 1, 2, 2, 3, 3)
  }

  "Exercise 3.20.v2" should "concat lists - v2" in {
    def map[A](l: List[A])(f: A => List[A]): List[A] =
      l.foldRight(Nil: List[A])((h, t) => f(h) ::: t)
    def flatMap[A](l: List[A])(f: A => List[A]): List[A] =
      map(l)(f)
    flatMap(List(1, 2, 3))(i => List(i, i)) shouldBe List(1, 1, 2, 2, 3, 3)
  }

  "Exercise 3.22" should "function that accepts two lists of integers and constructs a new list by adding corresponding elements" in {
    def addPairwise(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (h1 +: t1, h2 +: t2) => (h1 + h2) +: addPairwise(t1, t2)
    }
    addPairwise(List(1, 2, 3), List(4, 5, 6)) shouldBe List(5, 7, 9)
  }
  "Exercise 3.23" should "zipWith" in {
    def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] =
      (a, b) match {
        case (Nil, _)             => Nil
        case (_, Nil)             => Nil
        case (h1 :: t1, h2 :: t2) => f(h1, h2) :: zipWith(t1, t2)(f)
      }
    zipWith(List("a", "b", "c"), List("A", "B", "C"))(_ + _) shouldBe List("aA", "bB", "cC")
    zipWith(List(1, 2, 3), List(4, 5, 6))(_.toString + _.toString()) shouldBe List("14", "25", "36")
  }
  "Exercise 3.24" should "asSubsequence function for checking whether a List contains another List as a subsequence" in {
    @annotation.tailrec
    def startsWith[A](l: List[A], prefix: List[A]): Boolean =
      (l, prefix) match {
        case (_, Nil)                      => true
        case (h :: t, h2 :: t2) if h == h2 => startsWith(t, t2)
        case _                             => false
      }

    @annotation.tailrec
    def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
      case Nil                       => sub == Nil
      case _ if startsWith(sup, sub) => true
      case h :: t                    => hasSubsequence(t, sub)
    }

    def l = List(1, 2, 3, 4, 5)

    hasSubsequence(l, List(2, 3)) shouldBe true
    hasSubsequence(l, List(0, 1)) shouldBe false
    hasSubsequence(l, Nil) shouldBe true
  }
  "Exercise 3.25" should "size to count the number of nodes (leaves and branches) in a tree" in {
    def size[A](t: Tree[A]): Int = t match {
      case Leaf(_)      => 1
      case Branch(l, r) => 1 + size(l) + size(r)
    }

    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    size(t) shouldBe 5
  }
  "Exercise 3.26" should "maximum that returns the maximum element in a Tree[Int]" in {
    def maximum(t: Tree[Int]): Int = t match {
      case Leaf(n)      => n
      case Branch(l, r) => maximum(l) max maximum(r)
    }
    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(5))
    maximum(t) shouldBe 5
  }
  "Exercise 3.27" should "depth that returns the maximum path length from the root of a tree to any leaf." in {
    def depth[A](t: Tree[A]): Int = t match {
      case Leaf(_)      => 0
      case Branch(l, r) => 1 + (depth(l) max depth(r))
    }
    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    depth(t) shouldBe 2
  }

  "Exercise 3.28" should "map(t)(_ * 2) shouldBe Branch(Branch(Leaf(2), Leaf(4)), Leaf(6))" in {
    def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
      case Leaf(a)      => Leaf(f(a))
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
    }

    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    map(t)(_ * 2) shouldBe Branch(Branch(Leaf(2), Leaf(4)), Leaf(6))
  }

  "Exercise 3.29" should "sizeViaFold(t) shouldBe 5" in {
    def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
      case Leaf(a)      => f(a)
      case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
    }
    def sizeViaFold[A](t: Tree[A]): Int = fold(t)(a => 1)(1 + _ + _)

    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    println(s"sizeViaFold: ${sizeViaFold(t)}")
    sizeViaFold(t) shouldBe 5
  }
  "Exercise 3.29" should "maximumViaFold(t) shouldBe 3" in {
    def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
      case Leaf(a)      => f(a)
      case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
    }
    def maximumViaFold(t: Tree[Int]): Int = fold(t)(a => a)(_ max _)

    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    println(s"maximumViaFold: ${maximumViaFold(t)}")
    maximumViaFold(t) shouldBe 3
  }
  "Exercise 3.29" should "depthViaFold(t) shouldBe 2" in {
    def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
      case Leaf(a)      => f(a)
      case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
    }
    def depthViaFold[A](t: Tree[A]): Int =
      fold(t)(a => 0)((d1, d2) => {
        println(s"d1: $d1, d2: $d2")
        1 + (d1.max(d2))
      })

    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    println(s"depthViaFold: ${depthViaFold(t)}")
    depthViaFold(t) shouldBe 2
  }
  "Exercise 3.29" should "mapViaFold(t)(_ % 2 == 0) shouldBe Branch(Branch(Leaf(false), Leaf(true)), Leaf(false))" in {
    def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
      case Leaf(a)      => f(a)
      case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
    }
    def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] =
      fold(t)(a => Leaf(f(a)): Tree[B])(Branch(_, _))

    def t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    println(s"mapViaFold: ${mapViaFold(t)(_ % 2 == 0)}")
    mapViaFold(t)(_ % 2 == 0) shouldBe Branch(
      Branch(Leaf(false), Leaf(true)),
      Leaf(false)
    )
  }
}

trait Tree[A]
case class Leaf[A](leaf: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
