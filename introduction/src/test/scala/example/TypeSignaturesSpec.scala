package example

import org.scalatest._

class TypeSignaturesSpec extends FlatSpec with Matchers {
  "A trait can be declared containing a type, where a concrete implementer will satisfy the type" should "be" in {
    trait Randomizer[A] {
      def draw(): A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw() = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    (intRand.draw < Int.MaxValue) should be(true)
  }
  "Class meta-information can be retrieved by class name by using classOf[className]" should "be" in {
    classOf[String].getCanonicalName should be("java.lang.String")
    classOf[String].getSimpleName should be("String")
  }
  "Class meta-information can be derived from an object reference using getClass()" should "be" in {
    val zoom = "zoom"
    zoom.isInstanceOf[String] should be(true)
    zoom.getClass.getCanonicalName should be("java.lang.String")
    zoom.getClass.getSimpleName should be("String")
  }
  "isInstanceOf[className] is used to determine if an object reference is an instance of a given class" should "be" in {
    trait Randomizer[A] {
      def draw(): A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw() = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    intRand.isInstanceOf[IntRandomizer] should be(true)
    intRand.isInstanceOf[Randomizer[Int]] should be(true)
    intRand.draw.isInstanceOf[Int] should be(true)
  }
}
