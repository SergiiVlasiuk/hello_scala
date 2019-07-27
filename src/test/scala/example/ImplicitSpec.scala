package example

import org.scalatest._

class ImplicitSpec extends FlatSpec with Matchers {
  "Creating a method isOdd for Int, which doesn't exist" should "be" in {
    class KoanIntWrapper(val original: Int) {
      def isOdd = original % 2 != 0
    }

    implicit def thisMethodNameIsIrrelevant(value: Int) =
      new KoanIntWrapper(value)

    19.isOdd should be(true)
    20.isOdd should be(false)
  }
  "Implicits rules can be imported into your scope with an import" should "be" in {
    object MyPredef {

      class KoanIntWrapper(val original: Int) {
        def isOdd = original % 2 != 0

        def isEven = !isOdd
      }

      implicit def thisMethodNameIsIrrelevant(value: Int) =
        new KoanIntWrapper(value)
    }

    import MyPredef._
    //imported implicits come into effect within this scope
    19.isOdd should be(true)
    20.isOdd should be(false)
  }
  "Implicits can be used to automatically convert a value's type to another" should "be" in {
    import java.math.BigInteger
    implicit def Int2BigIntegerConvert(value: Int): BigInteger = new BigInteger(value.toString)

    def add(a: BigInteger, b: BigInteger) = a.add(b)

    add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9) should be(true)
    add(3, 6) == 9 should be(false)
    add(3, 6) == Int2BigIntegerConvert(9) should be(true)
    add(3, 6) == (9: BigInteger) should be(true)
    add(3, 6).intValue == 9 should be(true)
  }
  "These are called Implicit Function Parameters" should "be" in {
    def howMuchCanIMake_?(hours: Int)(implicit dollarsPerHour: BigDecimal) = dollarsPerHour * hours

    implicit val hourlyRate = BigDecimal(34)
    howMuchCanIMake_?(30) should be(1020)
  }
  "Implicit Function Parameters can contain a list of implicits" should "be" in {
    def howMuchCanIMake_?(hours: Int)(implicit amount: BigDecimal, currencyName: String) =
      (amount * hours).toString() + " " + currencyName
    implicit val hourlyRate = BigDecimal(34)
    implicit val currencyName = "Dollars"
    howMuchCanIMake_?(30) should be("1020 Dollars")
  }
  "Default arguments, though, are preferred to Implicit Function Parameters" should "be" in {
    def howMuchCanIMake_?(hours: Int, amount: BigDecimal = 34, currencyName: String = "Dollars") =
      (amount * hours).toString() + " " + currencyName
    howMuchCanIMake_?(30) should be("1020 Dollars")
    howMuchCanIMake_?(30, 95) should be("2850 Dollars")
  }
}
