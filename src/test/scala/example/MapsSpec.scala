package example

import org.scalatest._

class MapsSpec extends FlatSpec with Matchers {
  "myMap.size" should "be 4" in {
    val myMap = Map("MI" -> "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa")
    myMap.size should be(4)
  }
  "myMap.size" should "be 3" in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "MI" → "Michigan")
    myMap.size should be(3)
  }
  "+" should "be new Map" in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "MI" → "Michigan")
    val aNewMap = myMap + ("IL" → "Illinois")
    aNewMap.contains("IL") should be(true)
  }
  "Map values can be iterated" should "be .." in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "MI" → "Michigan")

    val mapValues = myMap.values
    mapValues.size should be(3)
    mapValues.head should be("Michigan") //Failed presumption: The order in maps is not guaranteed

    val lastElement = mapValues.last
    lastElement should be("Wisconsin")
  }
  "Maps may be accessed" should "be .." in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa")
    myMap("MI") should be("Michigan")
    myMap("IA") should be("Iowa")
  }
  "Maps insertion with duplicate key " should "update previous entry with subsequent value" in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "MI" → "Meechigan")
    val mapValues = myMap.values
    mapValues.size should be(3)
    myMap("MI") should be("Meechigan")
  }
  "Map keys" should "CAN be of mixed type" in {
    val myMap = Map("Ann Arbor" → "MI", 49931 → "MI")
    myMap("Ann Arbor") should be("MI")
    myMap(49931) should be("MI")
  }
  "If a nonexistent map key is requested" should "NoSuchElementException will be thrown" in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa")
    intercept[NoSuchElementException] {
      myMap("TX")
    }
    myMap.getOrElse("TX", "missing data") should be("missing data")
    val myMap2 = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa") withDefaultValue "missing data"
    myMap2("TX") should be("missing data")
  }
  "Map elements" should "be removed easily" in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa")
    val aNewMap = myMap - "MI"
    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)
  }
  "Map elements can be removed" should "in multiple" in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa")
    val aNewMap = myMap -- List("MI", "OH")

    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)

    aNewMap.contains("WI") should be(true)
    aNewMap.size should be(2)
    myMap.size should be(4)
  }
  "Map elements can be removed" should " with a tuple" in {
    val myMap = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa")
    val aNewMap = myMap - ("MI", "WI") // Notice: single '-' operator for tuples
    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)
    aNewMap.contains("OH") should be(true)
    aNewMap.size should be(2)
    myMap.size should be(4)
  }
  "Map equivalency is " should "independent of order" in {
    val myMap1 = Map("MI" → "Michigan", "OH" → "Ohio", "WI" → "Wisconsin", "IA" → "Iowa")
    val myMap2 = Map("WI" → "Wisconsin", "MI" → "Michigan", "IA" → "Iowa", "OH" → "Ohio")
    myMap1.equals(myMap2) should be(true)
  }
}
