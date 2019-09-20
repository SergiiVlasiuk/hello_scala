package example

import org.scalatest._

class PatternMatchingSpec extends FlatSpec with Matchers {
  "Pattern matching" should "return something" in {
    val stuff = "blue"
    val myStuff = stuff match {
      case "red" => println("RED"); 1
      case "blue" ⇒ println("BLUE"); 2
      case "green" ⇒ println("GREEN"); 3
      case _ ⇒ println(stuff); 0 // case _ will trigger if all other cases fail.
    }
    myStuff should be(2)
    myStuff shouldBe 2
  }
  "Pattern matching" should "return complex values" in {
    val stuff = "blue"
    val myStuff = stuff match {
      case "red" ⇒ (255, 0, 0)
      case "green" ⇒ (0, 255, 0)
      case "blue" ⇒ (0, 0, 255)
      case _ ⇒ println(stuff); 0
    }
    myStuff should be(0, 0, 255)
  }
  "Pattern matching" should "match complex expressions" in {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", "Papa") ⇒ "Papa eating porridge"
      case ("porridge", "Mama") ⇒ "Mama eating porridge"
      case ("porridge", "Baby") ⇒ "Baby eating porridge"
      case _ ⇒ "what?"
    }

    goldilocks(("porridge", "Mama")) should be("Mama eating porridge")
  }
  "Pattern matching" should "wildcard parts of expressions" in {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", _) ⇒ "eating"
      case ("chair", "Mama") ⇒ "sitting"
      case ("bed", "Baby") ⇒ "sleeping"
      case _ ⇒ "what?"
    }

    goldilocks(("porridge", "Papa")) should be("eating")
    goldilocks(("chair", "Mama")) should be("sitting")
  }
  "Pattern matching" should "substitute parts of expressions" in {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", bear) ⇒ bear + " said someone's been eating my porridge"
      case ("chair", bear) ⇒ bear + " said someone's been sitting in my chair"
      case ("bed", bear) ⇒ bear + " said someone's been sleeping in my bed"
      case _ ⇒ "what?"
    }

    goldilocks(("porridge", "Papa")) should be(
      "Papa said someone's been eating my porridge"
    )
    goldilocks(("chair", "Mama")) should be(
      "Mama said someone's been sitting in my chair"
    )
  }
  "backquote can be used" should "to refer to a stable variable in scope" in {
    val foodItem = "porridge"

    def goldilocks(expr: Any) = expr match {
      case (`foodItem`, _) ⇒ "eating"
      case ("chair", "Mama") ⇒ "sitting"
      case ("bed", "Baby") ⇒ "sleeping"
      case _ ⇒ "what?"
    }

    goldilocks(("porridge", "Papa")) should be("eating")
    goldilocks(("chair", "Mama")) should be("sitting")
    goldilocks(("porridge", "Cousin")) should be("eating")
    goldilocks(("beer", "Cousin")) should be("what?")
  }
  "backquote can be used" should "be used to refer to a method parameter as a stable variable to create a case statement" in {
    def patternEquals(i: Int, j: Int) = j match {
      case `i` ⇒ true
      case _ ⇒ false
    }

    patternEquals(3, 3) should be(true)
    patternEquals(7, 9) should be(false)
    patternEquals(9, 9) should be(true)
  }
  "match against a List" should "be split into parts" in {
    val secondElement = List(1, 2, 3) match {
      case x :: xs ⇒ xs.head
      case _ ⇒ 0
    }

    secondElement should be(2)
  }
  "match against a List" should "To obtain the second element" in {
    val secondElement = List(1, 2, 3) match {
      case x :: y :: xs ⇒ y
      case _ ⇒ 0
    }

    secondElement should be(2)
  }
  "match against a List" should "only one item" in {
    val secondElement = List(1) match {
      case x :: y :: xs ⇒ y // only matches a list with two or more items
      case _ ⇒ 0
    }

    secondElement should be(0)
  }
  "match against a List" should "only matches a list with exactly two items" in {
    val r = List(1, 2, 3) match {
      case x :: y :: Nil ⇒ y // only matches a list with exactly two items
      case _ ⇒ 0
    }

    r should be(0)
  }
  "If a pattern is exactly one element longer than a List" should "it extracts the final Nil" in {
    val r = List(1, 2, 3) match {
      case x :: y :: z :: tail ⇒ tail
      case _ ⇒ 0
    }

    r == Nil should be(true)
  }

}
