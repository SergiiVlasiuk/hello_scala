package example

import org.scalatest._

class RepeatedParametersSpec extends FlatSpec with Matchers {
  def repeatedParameterMethod(x: Int, y: String, z: Any*) = {
    "%d %ss can give you %s".format(x, y, z.mkString(", "))
  }

  "A repeated parameter must be the last parameter and this will let you add as many extra parameters as needed" should "be" in {
    repeatedParameterMethod(
      3,
      "egg",
      "a delicious sandwich",
      "protein",
      "high cholesterol"
    ) should
      be("3 eggs can give you a delicious sandwich, protein, high cholesterol")
  }
  "A repeated parameter can accept a collection as the last parameter but will be considered a single object" should "be" in {
    repeatedParameterMethod(
      3,
      "egg",
      List("a delicious sandwich", "protein", "high cholesterol")
    ) should
      be(
        "3 eggs can give you List(a delicious sandwich, protein, high cholesterol)"
      )
  }
  "A repeated parameter can accept a collection - if you want it expanded, add :_*" should "be" in {
    repeatedParameterMethod(
      3,
      "egg",
      List("a delicious sandwich", "protein", "high cholesterol"): _*
    ) should
      be("3 eggs can give you a delicious sandwich, protein, high cholesterol")
  }
}
