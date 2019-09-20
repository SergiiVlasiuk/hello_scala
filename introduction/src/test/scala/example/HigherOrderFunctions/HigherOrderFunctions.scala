package example.HigherOrderFunctions

import org.scalatest._

class HigherOrderFunctions extends FlatSpec with Matchers {
  "sum(x => x, 1, 10)" should "be 55" in {
    def sum(f: Int => Int, a: Int, b: Int): Int = {
      def loop(x: Int, acc: Int): Int = {
        if (x > b) acc
        else loop(x + 1, acc + f(x))
      }

      loop(a, 0)
    }

    sum(x => x, 1, 10) shouldBe 55
  }
  "fib(5)" should "be(5)" in {
    def fib(n: Int): Int = {
      @annotation.tailrec
      def loop(n: Int, prev: Int, cur: Int): Int =
        if (n <= 0) prev
        else loop(n - 1, cur, prev + cur)

      loop(n, 0, 1)
    }

    fib(5) should be(5)
  }
  "isSorted" should "verify function samples" in {
    def isSorted[A](as: Array[A], ordering: (A, A) => Boolean): Boolean = {
      @annotation.tailrec
      def go(n: Int): Boolean =
        if (n >= as.length - 1) true
        else if (ordering(as(n), as(n + 1))) false
        else go(n + 1)

      go(0)
    }

    isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x > y) shouldBe true
    isSorted(Array(7, 5, 1, 3), (x: Int, y: Int) => x < y) shouldBe false
    isSorted(
      Array("Scala", "Exercises"),
      (x: String, y: String) => x.length > y.length
    ) shouldBe true
  }
  "Currying is a transformation which converts a function f of two arguments" should "be function of one argument that partially applies f" in {
    def curry[A, B, C](f: (A, B) => C): A => (B => C) =
      a => b => f(a, b)

    def f(a: Int, b: Int): Int = a + b

    def g(a: Int)(b: Int): Int = a + b

    curry(f)(1)(1) == f(1, 1) shouldBe true
    curry(f)(1)(1) == g(1)(1) shouldBe true
  }
  "Uncurrying" should "be the reverse transformation of curry" in {
    def uncurry[A, B, C](f: A => B => C): (A, B) => C =
      (a, b) => f(a)(b)

    def f(a: Int, b: Int): Int = a + b

    def g(a: Int)(b: Int): Int = a + b

    uncurry(g)(1, 1) == g(1)(1) shouldBe true
    uncurry(g)(1, 1) == f(1, 1) shouldBe true
  }
  "Function composing feeds the output" should "composed behavior" in {
    def compose[A, B, C](f: B => C, g: A => B): A => C =
      a => f(g(a))

    def f(b: Int): Int = b / 2

    def g(a: Int): Int = a + 2

    compose(f, g)(0) == compose(g, f)(0) shouldBe false
    compose(f, g)(2) shouldBe 2
    compose(g, f)(2) shouldBe 3
  }
}
