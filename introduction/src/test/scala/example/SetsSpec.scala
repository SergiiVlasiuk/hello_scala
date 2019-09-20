package example

import org.scalatest._

class SetsSpec extends FlatSpec with Matchers {
  "Sets can be created easily" should "be 4" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    mySet.size should be(4)
  }
  "Sets can be added" should "be new" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet + "Illinois"
    aNewSet.contains("Illinois") should be(true)
    mySet.contains("Illinois") should be(false)
  }
  "Sets may be of mixed type" should "be" in {
    val mySet = Set("Michigan", "Ohio", 12)
    mySet.contains(12) should be(true)
    mySet.contains("MI") should be(false)
    mySet(12) should be(true)
    mySet("MI") should be(false)
  }
  "Sets may be removed" should "easy" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - "Michigan"
    aNewSet.contains("Michigan") should be(false)
    mySet.contains("Michigan") should be(true)
  }
  "Sets may be removed" should "in multiple" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet -- List("Michigan", "Ohio")
    aNewSet.contains("Michigan") should be(false)
    aNewSet.contains("Wisconsin") should be(true)
    aNewSet.size should be(2)
  }
  "Sets may be removed" should "with a tuple" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - ("Michigan", "Ohio")
    aNewSet.contains("Michigan") should be(false)
    aNewSet.contains("Wisconsin") should be(true)
    aNewSet.size should be(2)
  }
  "Attempted removal of nonexistent elements" should "is handled gracefully" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - "Minnesota"
    aNewSet.equals(mySet) should be(true)
  }
  "Two sets can be intersected" should "be" in {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    val aNewSet = mySet1 intersect mySet2
    // NOTE: Scala 2.7 used **, deprecated for & or intersect in Scala 2.8
    aNewSet.equals(Set("Michigan", "Wisconsin")) should be(true)
  }
  "Two sets can be joined" should "be" in {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    val aNewSet = mySet1 union mySet2 // NOTE: You can also use the "|" operator
    aNewSet.equals(Set("Michigan", "Wisconsin", "Ohio", "Iowa", "Minnesota")) should be(
      true
    )
  }
  "A set is either a subset of another set or it is not" should "be" in {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    val mySet3 = Set("Wisconsin", "Michigan")
    mySet2 subsetOf mySet1 should be(false)
    mySet3 subsetOf mySet1 should be(true)
  }
  "The difference between two sets can be obtained easily" should "be" in {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan")
    val aNewSet = mySet1 diff mySet2 // Note: you can use the "&~" operator if you *really* want to.
    aNewSet.equals(Set("Ohio", "Iowa")) should be(true)
  }
  "Set equivalency is independent of order" should "be" in {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Ohio", "Iowa")

    mySet1.equals(mySet2) should be(true)
  }
}
