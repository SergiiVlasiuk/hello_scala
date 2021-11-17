package example

import org.scalatest._

class ExtractorsSpec extends FlatSpec with Matchers {
  "you create a case class, it automatically can be used with pattern matching since it has an extractor" should "be" in {
    case class Employee(firstName: String, lastName: String)
    val rob = Employee("Robin", "Williams")
    val result = rob match {
      case Employee("Robin", _) ⇒ "Where's Batman?"
      case _ ⇒ "No Batman Joke For You"
    }
    result should be("Where's Batman?")
  }
  "In Scala it's a method in any object called unapply, and that method is used to disassemble the object" should "be" in {
    class Car(val make: String,
              val model: String,
              val year: Short,
              val topSpeed: Short)
    object ChopShop {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
    }
    val ChopShop(a, b, c, d) = new Car("Chevy", "Camaro", 1978, 120)
    a should be("Chevy")
    b should be("Camaro")
    c should be(1978)
    d should be(120)
  }
  "An extractor can also be used in pattern matching" should "be" in {
    class Car(val make: String,
              val model: String,
              val year: Short,
              val topSpeed: Short)
    object ChopShop {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
    }
    val x = new Car("Chevy", "Camaro", 1978, 120) match {
      case ChopShop(s, t, u, v) ⇒ (s, t)
      case _ ⇒ ("Ford", "Edsel")
    }
    x._1 should be("Chevy")
    x._2 should be("Camaro")
  }
  "Since we aren't really using u and v in the previous pattern matching, they can be replaced with _" should "be" in {
    class Car(val make: String,
              val model: String,
              val year: Short,
              val topSpeed: Short)

    object ChopShop {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
    }

    val x = new Car("Chevy", "Camaro", 1978, 120) match {
      case ChopShop(s, t, _, _) ⇒ (s, t)
      case _ ⇒ ("Ford", "Edsel")
    }
    x._1 should be("Chevy")
    x._2 should be("Camaro")
  }
  "As long as the method signatures aren't the same, you can have as many unapply methods as you want" should "be" in {
    class Car(val make: String,
              val model: String,
              val year: Short,
              val topSpeed: Short)
    class Employee(val firstName: String,
                   val middleName: Option[String],
                   val lastName: String)
    object Tokenizer {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)

      def unapply(x: Employee) = Some(x.firstName, x.lastName)
    }
    val result = new Employee("Kurt", None, "Vonnegut") match {
      case Tokenizer(c, d) ⇒ "c: %s, d: %s".format(c, d)
      case _ ⇒ "Not found"
    }
    result should be("c: Kurt, d: Vonnegut")
  }
  "An extractor can be any stable object, including instantiated classes with an unapply method" should "be" in {
    class Car(val make: String,
              val model: String,
              val year: Short,
              val topSpeed: Short) {
      def unapply(x: Car) = Some(x.make, x.model)
    }
    val camaro = new Car("Chevy", "Camaro", 1978, 122)
    val result = camaro match {
      case camaro(make, model) ⇒ "make: %s, model: %s".format(make, model)
      case _ ⇒ "unknown"
    }
    result should be("make: Chevy, model: Camaro")
  }
  "A custom extractor is typically created in the companion object of the class" should "be" in {
    class Employee(val firstName: String,
                   val middleName: Option[String],
                   val lastName: String)

    object Employee {
      //factory methods, extractors, apply
      //Extractor: Create tokens that represent your object
      def unapply(x: Employee) =
        Some(x.lastName, x.middleName, x.firstName)
    }

    val singri = new Employee("Singri", None, "Keerthi")

    val Employee(a, b, c) = singri

    a should be("Keerthi")
    b should be(None)
    c should be("Singri")
  }
  "In this exercise we use unapply for pattern matching employee objects" should "be" in {
    class Employee(val firstName: String,
                   val middleName: Option[String],
                   val lastName: String)
    object Employee {
      //factory methods, extractors, apply
      //Extractor: Create tokens that represent your object
      def unapply(x: Employee) =
        Some(x.lastName, x.middleName, x.firstName)
    }
    val singri = new Employee("Singri", None, "Keerthi")
    val result = singri match {
      case Employee("Singri", None, x) ⇒ "Yay, Singri %s! with no middle name!".format(x)
      case Employee(x, None, "Singri") ⇒ "Yay, Singri %s! with no middle name! and mixed unapply result".format(x)
      case Employee("Singri", Some(x), _) ⇒ "Yay, Singri with a middle name of %s".format(x)
      case _ ⇒ "I don't care, going on break"
    }
    result should be("Yay, Singri Keerthi! with no middle name! and mixed unapply result")

    val m = (1->"one", 2 ->"two")
  }
}
