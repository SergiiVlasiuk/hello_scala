package working_with_lists

import working_with_lists.p01.{lastBuiltin, lastRecursive}
import org.scalatest.FunSpec

class p01Test extends FunSpec with p01Builder {

  describe("get last item") {
    it("lastBuiltin") {
      assertResult(expected)(lastBuiltin(ls))
    }

    it("lastRecursive") {
      assertResult(expected)(lastRecursive(ls))
    }
  }
}
