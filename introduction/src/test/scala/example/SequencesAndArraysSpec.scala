package example

import org.scalatest._

class SequencesAndArraysSpec extends FlatSpec with Matchers {
  "A list can be converted to an array" should "be" in {
    val l = List(1, 2, 3)
    val a = l.toArray
    a should equal(Array(1, 2, 3))
  }
  "Any sequence can be converted to a list" should "be" in {
    val a = Array(1, 2, 3)
    val s = a.toSeq
    val l = s.toList
    l should equal(List(1, 2, 3))
  }
  "You can create a sequence from a for loop" should "be" in {
    val s = for (v ← 1 to 4) yield v
    s.toList should be(List(1, 2, 3, 4))
  }
  "You can create a sequence from a for loop with a filter" should "be" in {
    val s = for (v ← 1 to 10 if v % 3 == 0) yield v
    s.toList should be(List(3, 6, 9))
  }
  "You can filter any sequence based on a predicate" should "be" in {
    val s = Seq("hello", "to", "you")
    val filtered = s.filter(_.length > 2)
    filtered should be(Seq("hello", "you"))
  }
  "filter Arrays in the same way" should "be" in {
    val a = Array("hello", "to", "you", "again")
    val filtered = a.filter(_.length > 3)
    filtered should be(Array("hello", "again"))
  }
  "map values in a sequence through a function" should "be" in {
    val s = Seq("hello", "world")
    val r = s map {
      _.reverse
    }

    r should be(Seq("olleh", "dlrow"))
  }
}
