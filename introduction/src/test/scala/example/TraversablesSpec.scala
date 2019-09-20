package example

import org.scalatest._

import scala.collection.immutable.Stream.cons
import scala.language.postfixOps

class TraversablesSpec extends FlatSpec with Matchers {
  "++ appends two Traversables together" should "be" in {
    val set = Set(1, 9, 10, 22)
    val list = List(3, 4, 5, 10)
    val result = set ++ list
    result.size should be(7)

    val result2 = list ++ set
    result2.size should be(8)
  }
  "map will apply the given function on all elements of a Traversable and return a new collection of the result" should "be" in {
    val set = Set(1, 3, 4, 6)
    val result = set.map(_ * 4)
    result.lastOption should be(Some(24))
  }
  "flatten will \"pack\" all child Traversables into a single Traversable" should "be" in {
    val list = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    list.flatten should be(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
  }
  "flatMap will not only apply the given function on all elements of a Traversable, but all elements within the elements and flatten the results" should "be" in {
    val list = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    val result = list.flatMap(_.map(_ * 4))
    result should be(List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40))
  }
  "flatMap of Options will filter out all Nones but keep the Somes" should "be" in {
    val list = List(1, 2, 3, 4, 5)
    val result = list.flatMap(it ⇒ if (it % 2 == 0) Some(it) else None)
    result should be(List(2, 4))
  }
  "collect will apply a partial function to all elements of a Traversable and return a different collection. a case fragment is a partial function" should "be" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.collect {
      case x: Int if (x % 2 == 0) ⇒ x * 3
    }
    result should be(List(12, 18, 24, 42))
  }
  "Two case fragments can be chained to create a more robust result" should "be" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val partialFunction1: PartialFunction[Int, Int] = {
      case x: Int if x % 2 == 0 ⇒ x * 3
    }
    val partialFunction2: PartialFunction[Int, Int] = {
      case y: Int if y % 2 != 0 ⇒ y * 4
    }
    val result = list.collect(partialFunction1 orElse partialFunction2)
    result should be(List(12, 18, 28, 24, 36, 52, 42))
  }
  "foreach will apply a function to all elements of a Traversable, but unlike the map function" should "be" in {
    // it will not return anything since the return type is Unit - an equivalent to a void return type in Java/C++:
    val list = List(4, 6, 7, 8, 9, 13, 14)
    list.foreach(num ⇒ println(num * 4))
    list should be(List(4, 6, 7, 8, 9, 13, 14))
  }
  "toArray will convert any Traversable to an Array, which is a special wrapper around a primitive Java array" should "be" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toArray
    result.isInstanceOf[Array[Int]] should be(true)
  }
  "toList will convert any Traversable to a List" should "be" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toList
    result.isInstanceOf[List[_]] should be(true)
  }
  "toList, as well as other conversion methods such as toSet and toArray, will not convert if the collection type is the same" should "be" in {
    val list = List(5, 6, 7, 8, 9)
    val result = list.toList
    result eq list should be(true)
  }
  "toIterable will convert any Traversable to an Iterable. This is a base trait for all Scala coll" should "be" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toIterable
    result.isInstanceOf[Iterable[_]] should be(true)
  }
  "toSeq will convert any Traversable to a Seq" should "be" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toSeq
    result.isInstanceOf[Seq[_]] should be(true)
  }
  "toIndexedSeq will convert any Traversable to an IndexedSeq which is an indexed sequence used in Vectors and Strings" should "be" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toIndexedSeq
    result.isInstanceOf[IndexedSeq[_]] should be(true)
  }
  "toStream will convert any Traversable to a Stream which is a lazy list where elements are evaluated as they are needed" should "be" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.toStream
    result.isInstanceOf[Stream[_]] should be(true)
    (result take 3) should be(Stream(4, 6, 7))
  }
  "toSet will convert any Traversable to a Set which is a collection of unordered, unique values" should "be" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.toSet
    result.isInstanceOf[Set[_]] should be(true)
  }
  "toMap will convert any Traversable to a Map" should "be" in {
    val list = List("Phoenix" → "Arizona", "Austin" → "Texas")
    val result = list.toMap
    result.isInstanceOf[Map[_, _]] should be(true)
  }
  "toMap will also convert a Set to a Map; it should be of parameterized type Tuple2" should "be" in {
    val set = Set("Phoenix" → "Arizona", "Austin" → "Texas")
    val result = set.toMap
    result.isInstanceOf[Map[_, _]] should be(true)
  }
  "isEmpty is pretty self-evident" should "be" in {
    val map = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    map.isEmpty should be(false)
    val set = Set()
    set.isEmpty should be(true)
  }
  "nonEmpty is pretty self-evident too" should "be" in {
    val map = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    map.nonEmpty should be(true)

    val set = Set()
    set.nonEmpty should be(false)
  }
  "size provides the size of the traversable" should "be" in {
    val map = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    map.size should be(2)
  }
  "hasDefiniteSize will return true if the traversable has a finite end" should "be" in {
    val map = Map("Phoenix" → "Arizona", "Austin" → "Texas")
    map.hasDefiniteSize should be(true)

    val stream = cons(0, cons(1, Stream.empty))
    stream.hasDefiniteSize should be(false)
  }
  "head will return the first element of an ordered collection, or some random element if order is not defined like in a Set or Map" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.head should be(10)
  }
  "headOption will return the first element as an Option of an ordered collection" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.headOption should be(Option(10))

    val list2 = List()
    list2.headOption should be(None)
  }
  "last will return the last element of an ordered collection, or some random element if order is not defined" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.last should be(22)
  }
  "lastOption will return the last element as an Option of an ordered collection" should "be" in {
    //    or some random element if order is not defined. If a last element is not available, then None is returned:
    val list = List(10, 19, 45, 1, 22)
    list.lastOption should be(Option(22))

    val list2 = List()
    list2.lastOption should be(None)
  }
  "find will locate the first item that matches the predicate p as Some, or None if an element is not found" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.find(_ % 2 != 0) should be(Some(19))
    val list2 = List(4, 8, 16)
    list2.find(_ % 2 != 0) should be(None)
  }
  "tail will return the rest of the collection without the head" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.tail should be(List(19, 45, 1, 22))
  }
  "init will return the rest of the collection without the last" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.init should be(List(10, 19, 45, 1))
  }
  "Given a from index, and a to index, slice will return the part of the collection including from, and excluding to" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.slice(1, 3) should be(List(19, 45))
  }
  "take will return the first number of elements given" should "be" in {
    val list = List(10, 19, 45, 1, 22)
    list.take(3) should be(List(10, 19, 45))
  }
  "take is used often with Streams, since they are also Traversable" should "be" in {
    def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))

    val a = streamer(2)
    (a take 3 toList) should be(List(2, 3, 4))
  }
  "drop will take the rest of the Traversable except the number of elements given" should "be" in {
    def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))

    val a = streamer(2)
    ((a drop 6) take 3).toList should be(List(8, 9, 10))
  }
  "takeWhile will continually accumulate elements until a predicate is no longer satisfied" should "be" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    list.takeWhile(_ < 100) should be(List(87, 44, 5, 4))
  }
  "dropWhile will continually drop elements until a predicate is no longer satisfied" should "be" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    list.dropWhile(_ < 100) should be(List(200, 10, 39, 100))
  }
  "filter will take out all elements that don't satisfy a predicate. (An Array is also Traversable.)" should "be" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    array.filter(_ < 100) should be(Array(87, 44, 5, 4, 10, 39))
  }
  "filterNot will take out all elements that satisfy a predicate" should "be" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    array.filterNot(_ < 100) should be(Array(200, 100))
  }
  "splitAt will split a Traversable at a position, returning a 2 product Tuple" should "be" in {
    // splitAt is also defined as (xs take n, xs drop n)
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result = array splitAt 3
    result._1 should be(Array(87, 44, 5))
    result._2 should be(Array(4, 200, 10, 39, 100))
  }
  "span will split a Traversable according to a predicate, returning a 2 product Tuple" should "be" in {
    // span is also defined as (xs takeWhile p, xs dropWhile p)
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result = array span (_ < 100)
    result._1 should be(Array(87, 44, 5, 4))
    result._2 should be(Array(200, 10, 39, 100))
  }
  "partition will split a Traversable according to a predicate, returning a 2 product Tuple" should "be" in {
    // The left-hand side contains the elements satisfied by the predicate whereas the right hand side contains the
    // rest of the elements. partition is also defined as (xs filter p, xs filterNot p)
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result = array partition (_ < 100)
    result._1 should be(Array(87, 44, 5, 4, 10, 39))
    result._2 should be(Array(200, 100))
  }
  "groupBy will categorize a Traversable according to a given function and return a map with the results" should "be" in {
    // This exercise uses partial function chaining:
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val oddAndSmallPartial: PartialFunction[Int, String] = {
      case x: Int if x % 2 != 0 && x < 100 ⇒ "Odd and less than 100"
    }
    val evenAndSmallPartial: PartialFunction[Int, String] = {
      case x: Int if x != 0 && x % 2 == 0 && x < 100 ⇒ "Even and less than 100"
    }
    val negativePartial: PartialFunction[Int, String] = {
      case x: Int if x < 0 ⇒ "Negative Number"
    }
    val largePartial: PartialFunction[Int, String] = {
      case x: Int if x > 99 ⇒ "Large Number"
    }
    val zeroPartial: PartialFunction[Int, String] = {
      case x: Int if x == 0 ⇒ "Zero"
    }
    val result = array groupBy {
      oddAndSmallPartial orElse
        evenAndSmallPartial orElse
        negativePartial orElse
        largePartial orElse
        zeroPartial
    }
    println(result.keySet)
    println(result.values)
    println(result.toString())
    (result("Even and less than 100") size) should be(3)
    (result("Large Number") size) should be(2)
  }
  "forall will determine if a predicate is valid for all members of a Traversable" should "be" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result = list forall (_ < 100)
    result should be(false)
  }
  "exists will determine if a predicate is valid for some members of a Traversable" should "be" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result = list exists (_ < 100)
    result should be(true)
  }
  "count will count the number of elements that satisfy a predicate in a Traversable" should "be" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result = list count (_ < 100)
    result should be(6)
  }
  "/: or foldLeft will combine an operation starting with a seed and combining from the left" should "be" in {
    val list = List(5, 4, 3, 2, 1)
    val result = (0 /: list) { (`running total`, `next element`) ⇒
      `running total` - `next element`
    }
    result should be(-15)
    val result2 = list.foldLeft(0) { (`running total`, `next element`) ⇒
      `running total` - `next element`
    }
    result2 should be(-15)
    val result3 = (0 /: list)(_ - _) //Short hand
    result3 should be(-15)
    val result4 = list.foldLeft(0)(_ - _)
    result4 should be(-15)
    (((((0 - 5) - 4) - 3) - 2) - 1) should be(-15)
  }
  ":\\ or foldRight will combine an operation starting with a seed and combining from the right" should "be" in {
    val list = List(5, 4, 3, 2, 1)
    val result = (list :\ 0) { (`next element`, `running total`) ⇒
      `next element` - `running total`
    }
    result should be(3)

    val result2 = list.foldRight(0) { (`next element`, `running total`) ⇒
      `next element` - `running total`
    }
    result2 should be(3)

    val result3 = (list :\ 0)(_ - _) //Short hand
    result3 should be(3)

    val result4 = list.foldRight(0)(_ - _)
    result4 should be(3)

    (5 - (4 - (3 - (2 - (1 - 0))))) should be(3)
  }
  "reduceLeft is similar to foldLeft, except that the seed is the head value" should "be" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.reduceLeft {
      _ + _
    } should be(15)
    intList.reduceLeft {
      _ - _
    } should be(-5)

    val stringList = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
    stringList.reduceLeft {
      _ + _
    } should be("DoReMeFaSoLaTeDo")
  }
  "reduceRight is similar to foldRight, except that the seed is the last value" should "be" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.reduceRight {
      _ + _
    } should be(15)
    intList.reduceRight {
      _ - _
    } should be(3)

    val stringList = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
    stringList.reduceRight {
      _ + _
    } should be("DoReMeFaSoLaTeDo")
  }
  "sum will add all the elements, product will multiply, min would determine the smallest element, and max the largest" should "be" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.sum should be(15)
    intList.product should be(120)
    intList.max should be(5)
    intList.min should be(1)
  }
  "The naive recursive implementation of reduceRight is not tail recursive and would lead to a stack overflow" should "be" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.reduceRight((x, y) => x - y) should be(3)
    intList.reverse.reduceLeft((x, y) => y - x) should be(3)
    intList.reverse.reduce((x, y) => y - x) should be(3)
  }
  "transpose will take a traversable of traversables and group them by their position in it's own traversable" should "be" in {
    // e.g.: ((x1, x2),(y1, y2)).transpose = (x1, y1), (x2, y2) or ((x1, x2, x3),(y1, y2, y3),(z1, z2, z3)).transpose
    //   = ((x1, y1, z1), (x2, y2, z2), (x3, y3, z3))
    val list = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    list.transpose should be(List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9)))
    val list2 = List(List(1), List(4))
    list2.transpose should be(List(List(1, 4)))
  }
  "mkString will format a Traversable using a given string as the delimiter" should "be" in {
    val list = List(1, 2, 3, 4, 5)
    list.mkString(",") should be("1,2,3,4,5")
  }
  "mkString will also take a beginning and ending string to surround the list" should "be" in {
    val list = List(1, 2, 3, 4, 5)
    list.mkString(">", ",", "<") should be(">1,2,3,4,5<")
  }
  "addString will take a StringBuilder to add the contents of list into the builder" should "be" in {
    val stringBuilder = new StringBuilder()
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    stringBuilder.append("I want all numbers 6-12: ")
    list.filter(it ⇒ it > 5 && it < 13).addString(stringBuilder, ",")
    stringBuilder.mkString should be(
      "I want all numbers 6-12: 6,7,8,9,10,11,12"
    )
  }
}
