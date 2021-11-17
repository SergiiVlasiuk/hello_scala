package working_with_lists

import org.scalatest.{BeforeAndAfterEach, Suite}

trait p01Builder extends BeforeAndAfterEach { this: Suite =>

  var ls: List[Int] = _
  val expected: Int = 8

  override def beforeEach(): Unit = {
    ls = List(1, 1, 2, 3, 5, expected)
    super.beforeEach()
  }

  override def afterEach(): Unit = {
    ls = List.empty
    super.afterEach()
  }

}
