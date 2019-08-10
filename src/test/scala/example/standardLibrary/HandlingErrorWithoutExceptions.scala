package example.standardLibrary

import example.standardLibrary.Employee.lookupByName
import org.scalatest.{FlatSpec, Matchers}

import scala.util.{Success, Try}

class HandlingErrorWithoutExceptions extends FlatSpec with Matchers {
  "The Option data type" should "be ..." in {
    /**
      * We can look for our employees, and try to obtain their departments. We will assume that we won't find any errors,
      * and if it's the case, we don't have to worry as the computation will end there. Try to use `map` on the result of
      * calling `lookupByName` to create a function to obtain the department of each employee. Hint: to access the
      * optional employee, use Scala's underscore notation. i.e.:
      *
      * _.getOrElse(Employee("John", "Doe", None))
      *
      * Employee is defined as:
      *
      * case class Employee(name: String, department: String, manager: Option[String])
      */
    def getDepartment: (Option[Employee]) => Option[String] = _.map(_.department)

    getDepartment(lookupByName("Joe")) shouldBe Some("Finances")
    getDepartment(lookupByName("Mary")) shouldBe Some("IT")
    getDepartment(lookupByName("Foo")) shouldBe None
  }
  "Try to find out who is managing each employee" should "be ..." in {
    //    def getManager: (Option[Employee]) => Option[String] = _.map(_.manager).getOrElse(None)
    def getManager: (Option[Employee]) => Option[String] = _.flatMap(_.manager)

    getManager(lookupByName("Joe")) shouldBe Some("Julie")
    getManager(lookupByName("Mary")) shouldBe None
    getManager(lookupByName("Foo")) shouldBe None
  }
  "orElse returns the original Option if not None, or returns the provided Option as an alternative" should "be ..." in {
    def getManager(employee: Option[Employee]): Option[String] = employee.flatMap(_.manager)

    getManager(lookupByName("Joe")).orElse(Some("Mr. CEO")) shouldBe Some("Julie")
    getManager(lookupByName("Mary")).orElse(Some("Mr. CEO")) shouldBe Some("Mr. CEO")
    getManager(lookupByName("Foo")).orElse(Some("Mr. CEO")) shouldBe Some("Mr. CEO")
  }
  "Test it out by discarding those employees who belong to the IT department" should "be ..." in {
    lookupByName("Joe").filter(_.department != "IT") shouldBe Some(Employee("Joe", "Finances", Some("Julie")))
    lookupByName("Mary").filter(_.department != "IT") shouldBe None
    lookupByName("Foo").filter(_.department != "IT") shouldBe None
  }
  "the result should be a Some with a list of all the values" should "be ..." in {
    def sequence(a: List[Option[Int]]): Option[List[Int]] = a match {
      case Nil => Some(Nil)
      case h :: t => h flatMap (hh => sequence(t) map (hh :: _))
    }

    sequence(List(Some(1), Some(2), Some(3))) shouldBe Some(List(1, 2, 3))
    sequence(List(Some(1), Some(2), None)) shouldBe None
  }
  "try traverse out, by trying to parse a List[String] into a Option[List[Int]]" should "be ..." in {
    def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
      a flatMap (aa => b map (bb => f(aa, bb)))

    def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
      case Nil =>
        println(s"traverse[A, B]: Nil")
        Some(Nil)
      case h :: t =>
        println(s"traverse[A, B]: $h, $t")
        map2(f(h), traverse(t)(f))(_ :: _)
    }

    val list1 = List("1", "2", "3")
    val list2 = List("I", "II", "III", "IV")

    def parseInt(a: String): Option[Int] = Try(a.toInt) match {
      case Success(r) => Some(r)
        println(s"parseInt($a: String)")
        Some(r)
      case _ =>
        println(s"parseInt($a: String) None")
        None
    }

    traverse(list1)(i => parseInt(i)) shouldBe Some(List(1, 2, 3))
    traverse(list2)(i => parseInt(i)) shouldBe None
  }
  "Try to use map on the Either type to obtain the department of each employee" should "be ..." in {
    def lookupByNameViaEither(name: String): Either[String, Employee] = name match {
      case "Joe" => Right(Employee("Joe", "Finances", Some("Julie")))
      case "Mary" => Right(Employee("Mary", "IT", None))
      case "Izumi" => Right(Employee("Izumi", "IT", Some("Mary")))
      case _ => Left("Employee not found")
    }

    def getDepartment: (Either[String, Employee]) => Either[String, String] = _.map(_.department)

    getDepartment(lookupByNameViaEither("Joe")) shouldBe Right("Finances")
    getDepartment(lookupByNameViaEither("Mary")) shouldBe Right("IT")
    getDepartment(lookupByNameViaEither("Foo")) shouldBe Left("Employee not found")
  }
  "to find two different errors in its execution" should "be ..." in {
    def lookupByNameViaEither(name: String): Either[String, Employee] = name match {
      case "Joe" => Right(Employee("Joe", "Finances", Some("Julie")))
      case "Mary" => Right(Employee("Mary", "IT", None))
      case "Izumi" => Right(Employee("Izumi", "IT", Some("Mary")))
      case _ => Left("Employee not found")
    }

    def getManager(employee: Either[String, Employee]): Either[String, String] =
      employee.flatMap(e =>
        e.manager match {
          case Some(e) => Right(e)
          case _ => Left("Manager not found")
        })

    getManager(lookupByNameViaEither("Joe")) shouldBe Right("Julie")
    getManager(lookupByNameViaEither("Mary")) shouldBe Left("Manager not found")
    getManager(lookupByNameViaEither("Foo")) shouldBe Left("Employee not found")
  }
  "Let's assume that everyone inside our company ends up responding to a \"Mr. CEO\" manager" should "be ..." in {
    def lookupByNameViaEither(name: String): Either[String, Employee] = name match {
      case "Joe" => Right(Employee("Joe", "Finances", Some("Julie")))
      case "Mary" => Right(Employee("Mary", "IT", None))
      case "Izumi" => Right(Employee("Izumi", "IT", Some("Mary")))
      case _ => Left("Employee not found")
    }

    def getManager(employee: Either[String, Employee]): Either[String, String] =
      employee.flatMap(e =>
        e.manager match {
          case Some(e) => Right(e)
          case _ => Left("Manager not found")
        })

    getManager(lookupByNameViaEither("Joe")).getOrElse(Right("Mr. CEO")) shouldBe "Julie"
    //    getManager(lookupByNameViaEither("Joe")).getOrElse(Right("Mr. CEO")) shouldBe Right("Julie")
    getManager(lookupByNameViaEither("Mary")).getOrElse(Right("Mr. CEO")) shouldBe Right("Mr. CEO")
    getManager(lookupByNameViaEither("Foo")).getOrElse(Right("Mr. CEO")) shouldBe Right("Mr. CEO")
  }
//  "let's test map2" should "be ..." in {
//    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
//      for {
//        a <- this;
//        b1 <- b
//      } yield f(a, b1)
//
//    def lookupByNameViaEither(name: String): Either[String, Employee] = name match {
//      case "Joe" => Right(Employee("Joe", "Finances", Some("Julie")))
//      case "Mary" => Right(Employee("Mary", "IT", None))
//      case "Izumi" => Right(Employee("Izumi", "IT", Some("Mary")))
//      case _ => Left("Employee not found")
//    }
//
//    def employeesShareDepartment(employeeA: Employee, employeeB: Employee) =
//      employeeA.department == employeeB.department
//
//    lookupByNameViaEither("Joe").map2(lookupByNameViaEither("Mary"))(employeesShareDepartment) shouldBe ???
//    lookupByNameViaEither("Mary").map2(lookupByNameViaEither("Izumi"))(employeesShareDepartment) shouldBe ???
//    lookupByNameViaEither("Foo").map2(lookupByNameViaEither("Izumi"))(employeesShareDepartment) shouldBe ???
//  }
}

case class Employee(name: String, department: String, manager: Option[String])

object Employee {
  def lookupByName(name: String): Option[Employee] = name match {
    case "Joe" => Some(Employee("Joe", "Finances", Some("Julie")))
    case "Mary" => Some(Employee("Mary", "IT", None))
    case "Izumi" => Some(Employee("Izumi", "IT", Some("Mary")))
    case _ => None
  }
}

//trait Partial[+A, +B]
//
//case class Errors[+A](get: Seq[A]) extends Partial[A, Nothing]
//
//case class Success[+B](get: B) extends Partial[Nothing, B]
