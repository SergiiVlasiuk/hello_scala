package example.matcherFunc

import example.matcherFunc.CaseClassMatcherExampleApp.matcherFunc
import org.scalatest.{FlatSpec, Matchers}

class CaseClassMatcherExampleAppTest extends FlatSpec with Matchers {

  "first" should "match specified name" in {
    matcherFunc(User("dave", "some@email.com")) shouldBe "first case: This is dave, his email: some@email.com"
  }
  it should "match by name and email" in {
    matcherFunc(User("dave", "anymail")) shouldBe "first case: This is dave, his email: anymail"
  }
  "second" should "match by name and email" in {
    matcherFunc(User("notadave", "")) shouldBe "second case: User is notadave, his email: "
  }
  "third" should "match by name and email" in {
    matcherFunc(User("john", "email@any.com")) shouldBe "third case: User is john, his email: email@any.com"
  }
  "fourth" should "superuser" in {
    matcherFunc(SuperUser("neo", "super@secret.email", "every possible")) shouldBe "fourth case: SuperUser is neo , his email: super@secret.email, privelegies: every possible"
  }
  "fifth" should "match by name and email" in {
    matcherFunc(SomethingStrange) shouldBe "fifth case: We don’t know what is it: SomethingStrange"
  }
  "case class more testing" should "filter with case class" in {
    case class PhoneExt(name: String, ext: Int)
    val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))

    extensions.filter { case PhoneExt(name, extension) => extension < 200 } shouldBe List(PhoneExt("steve",100))
  }
}
