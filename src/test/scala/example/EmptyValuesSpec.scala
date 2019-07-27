package example

import org.scalatest._

class EmptyValuesSpec extends FlatSpec with Matchers {
  "An empty list can be represented by another nothing value: Nil" should "be" in {
    List() === Nil shouldBe true
  }
  "None equals None" should "be" in {
    None === None shouldBe true
  }
  "None should be identical to None" should "be" in {
    None eq None shouldBe true
  }
  "None can be converted to a String" should "be" in {
    assert(None.toString === "None")
  }
  "None can be converted to an empty list" should "be" in {
    None.toList === Nil shouldBe true
  }
  "None is considered empty" should "be" in {
    assert(None.isEmpty === true)
  }
  "None can be cast to Any, AnyRef or AnyVal" should "be" in {
    None.asInstanceOf[Any] === None shouldBe true
    None.asInstanceOf[AnyRef] === None shouldBe true
    None.asInstanceOf[AnyVal] === None shouldBe true
  }
  "None can be used with Option instead of null references" should "be" in {
    val optional: Option[String] = None
    assert(optional.isEmpty === true)
    assert(optional === None)
  }
  "Some is the opposite of None for Option types" should "be" in {
    val optional: Option[String] = Some("Some Value")
    assert((optional == None) === false, "Some(value) should not equal None")
    assert(optional.isEmpty === false, "Some(value) should not be empty")
  }
  "Option.getOrElse can be used to provide a default in the case of None" should "be" in {
    val optional: Option[String] = Some("Some Value")
    val optional2: Option[String] = None
    assert(optional.getOrElse("No Value") === "Some Value", "Should return the value in the option")
    assert(optional2.getOrElse("No Value") === "No Value", "Should return the specified default value")
  }
}
