package example

import org.scalatest._

class InfixTypesSpec extends FlatSpec with Matchers {
  "We can make a type infix, meaning that the type can be displayed in complement between two types in order to make a readable declaration" should "be" in {
    case class Person(name: String)
    class Loves[A, B](val a: A, val b: B)

    def announceCouple(couple: Person Loves Person) =
      //Notice our type: Person loves Person!
      couple.a.name + " is in love with " + couple.b.name

    val romeo = new Person("Romeo")
    val juliet = new Person("Juliet")

    announceCouple(new Loves(romeo, juliet)) should be(
      "Romeo is in love with Juliet"
    )
  }
  "we can make this a bit more elegant by creating an infix operator method to use with our infix type" should "be" in {
    case class Person(name: String) {
      def loves(person: Person) = new Loves(this, person)
    }
    class Loves[A, B](val a: A, val b: B)
    def announceCouple(couple: Person Loves Person) =
      //Notice our type: Person loves Person!
      couple.a.name + " is in love with " + couple.b.name

    val romeo = new Person("Romeo")
    val juliet = new Person("Juliet")
    announceCouple(romeo loves juliet) should be("Romeo is in love with Juliet")
  }
}
