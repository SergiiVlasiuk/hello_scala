package example

import org.scalatest._

class RangesSpec extends FlatSpec with Matchers {
  "A range's upper bound is not inclusive" should "be" in {
    val someNumbers = Range(0, 10)
    val second = someNumbers(1)
    val last = someNumbers.last

    someNumbers.size should be(10)
    second should be(1)
    last should be(9)
  }
  "Ranges can be specified using 'until'" should "be" in {
    val someNumbers = Range(0, 10)
    val otherRange = 0 until 10

    (someNumbers == otherRange) should be(true)
  }
  "Range can specify a step for an increment:" should "be" in {
    val someNumbers = Range(2, 10, 3)
    val second = someNumbers(1)
    val last = someNumbers.last

    someNumbers.size should be(3)
    second should be(5)
    last should be(8)
  }
  "A range does not include its upper bound, even in a step increment" should "be" in {
    val someNumbers = Range(0, 34, 2)
    someNumbers.contains(33) should be(false)
    someNumbers.contains(32) should be(true)
    someNumbers.contains(34) should be(false)
  }
  "Range can specify to include its upper bound value" should "be" in {
    val someNumbers = Range(0, 34).inclusive
    someNumbers.contains(34) should be(true)
  }
  "Inclusive ranges can be specified using 'to'" should "be" in {
    val someNumbers = Range(0, 34).inclusive
    val otherRange = 0 to 34

    (someNumbers == otherRange) should be(true)
  }
}
