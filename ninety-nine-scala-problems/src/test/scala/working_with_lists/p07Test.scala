package working_with_lists

import org.scalatest.FunSpec

class p07Test extends FunSpec {

  describe("p07Test") {
    it("should flatten") {
      assertResult(List(1, 1, 2, 3, 5, 8))(p07.flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
    }
  }
}
