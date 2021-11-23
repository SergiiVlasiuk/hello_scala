package arithmetic

import org.scalatest.WordSpec

class p41Test extends WordSpec {

  "p41Test" should {

    "Calculate a list of Goldbach compositions" in {
      p41.printGoldbachList(9 to 20)
    }
    "Calculate a list of Goldbach compositions with limit" in {
      p41.printGoldbachListLimited(999 to 2000, 50)
    }
  }
}
