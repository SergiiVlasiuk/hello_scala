package working_with_lists

import org.scalatest.FunSpec

class p11Test extends FunSpec {
  val input: List[Symbol]          = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  val expected: List[Serializable] = List((4, 'a), 'b, (2, 'c), (2, 'a), 'd, (4, 'e))

  describe("p11Test") {
    it("should encodeModified") {
      assertResult(expected)(p11.encodeModified(input))
    }

    it("should encodeModified2") {
      val exp: Seq[Either[Symbol, (Int, Symbol)]] =
        List(Right((4, 'a)), Left('b), Right((2, 'c)), Right((2, 'a)), Left('d), Right((4, 'e)))
      assertResult(exp)(p11.encodeModified2(input))
    }
  }
}
