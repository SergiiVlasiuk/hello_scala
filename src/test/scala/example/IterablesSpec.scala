package example

import org.scalatest._

class IterablesSpec extends FlatSpec with Matchers {
  "Iterator has two important methods: hasNext, next" should "be" in {
    val list = List(3, 5, 9, 11, 15, 19, 21)
    val it = list.iterator
    if (it.hasNext) {
      it.next should be(3)
    }
  }
  "grouped will return fixed-size Iterable chucks of an Iterable" should "be" in {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list grouped 3
    it.next() should be(List(3, 5, 9))
    it.next() should be(List(11, 15, 19))
    it.next() should be(List(21, 24, 32))
  }
  "sliding will return an Iterable that shows a sliding window of an Iterable" should "be" in {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list sliding 3
    it.next() should be(List(3, 5, 9))
    it.next() should be(List(5, 9, 11))
    it.next() should be(List(9, 11, 15))
  }
  "sliding can take the size of the window as well the size of the step during each iteration" should "be" in {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list sliding(3, 3)
    it.next() should be(List(3, 5, 9))
    it.next() should be(List(11, 15, 19))
    it.next() should be(List(21, 24, 32))
  }
  "takeRight is the opposite of 'take' in Traversable. It retrieves the last elements of an Iterable" should "be" in {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    (list takeRight 3) should be(List(21, 24, 32))
  }
  "dropRight will drop a specified number of elements from the right" should "be" in {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    (list dropRight 3) should be(List(3, 5, 9, 11, 15, 19))
  }
  "zip will stitch two iterables into an iterable of pairs of corresponding elements from both iterables" should "be" in {
    //    e.g. Iterable(x1, x2, x3) zip Iterable(y1, y2, y3) will return ((x1, y1), (x2, y2), (x3, y3))
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann", "Stella")
    (xs zip ys) should be(List((3, "Bob"), (5, "Ann"), (9, "Stella")))
  }
  "If two Iterables aren't the same size, then zip will only zip what can be paired" should "be" in {
    // e.g. Iterable(x1, x2, x3) zip Iterable(y1, y2) will return ((x1, y1), (x2, y2))
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann")
    (xs zip ys) should be(List((3, "Bob"), (5, "Ann")))
  }
  "If two Iterables aren't the same size, then zipAll can provide fillers for what it couldn't find a complement for" should "be" in {
    // e.g. Iterable(x1, x2, x3) zipAll (Iterable(y1, y2), x, y) will return ((x1,y1), (x2, y2), (x3, y)))
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann")
    (xs zipAll (ys, -1, "?")) should be(List((3, "Bob"), (5, "Ann"), (9, "?")))

    val xt = List(3, 5)
    val yt = List("Bob", "Ann", "Stella")
    (xt zipAll (yt, -1, "?")) should be(List((3, "Bob"), (5, "Ann"), (-1, "Stella")))
  }
  "zipWithIndex will zip an Iterable with its integer index" should "be" in {
    val xs = List("Manny", "Moe", "Jack")
    xs.zipWithIndex should be(List(("Manny", 0), ("Moe", 1), ("Jack", 2)))
  }
  "sameElements will return true if the two Iterables produce the same elements in the same order" should "be" in {
    val xs = List("Manny", "Moe", "Jack")
    val ys = List("Manny", "Moe", "Jack")
    (xs sameElements ys) should be(true)

    val xt = List("Manny", "Moe", "Jack")
    val yt = List("Manny", "Jack", "Moe")
    (xt sameElements yt) should be(false)

    val xs1 = Set(3, 2, 1, 4, 5, 6, 7)
    val ys1 = Set(7, 2, 1, 4, 5, 6, 3)
    (xs1 sameElements ys1) should be(true)

    val xt1 = Set(1, 2, 3)
    val yt1 = Set(3, 2, 1)
    (xt1 sameElements yt1) should be(false)
  }
}
