package working_with_lists

import org.scalatest.FunSpec

class p06Test extends FunSpec {

  it("should isPalindrome - correct input") {
    assertResult(true)(p06.isPalindrome(List(1, 2, 3, 2, 1)))
  }

  it("should isPalindrome  - incorrect input") {
    assertResult(false)(p06.isPalindrome(List(1, 1, 2, 3, 2, 1)))
  }
}
