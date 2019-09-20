package example

import org.scalatest._

class UniformAccessPrinciplesSpec extends FlatSpec with Matchers {
  "As a result, a parameterless function definition can be changed to a val, or vice versa" should "be" in {
    class Test1(val age: Int = 10)
    class Test2(_age: Int) {
      def age: Int = _age
    }
    new Test1(10).age should be(10)
    new Test2(11).age should be(11)
  }
}
