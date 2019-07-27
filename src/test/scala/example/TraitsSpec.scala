package example

import org.scalatest._

class TraitsSpec extends FlatSpec with Matchers {
  "A class uses the extends keyword to mixin a trait if it is the only relationship the class inherits" should "be" in {
    case class Event(name: String)

    trait EventListener {
      def listen(event: Event): String
    }

    class MyListener extends EventListener {
      def listen(event: Event): String = {
        event match {
          case Event("Moose Stampede") ⇒
            "An unfortunate moose stampede occurred"
          case _ ⇒ "Nothing of importance occurred"
        }
      }
    }

    val evt = Event("Moose Stampede")
    val myListener = new MyListener
    myListener.listen(evt) should be("An unfortunate moose stampede occurred")
  }
  "A class can only extend from one class or trait, any subsequent extension should use the keyword with" should "be" in {
    case class Event(name: String)

    trait EventListener {
      def listen(event: Event): String
    }

    class OurListener

    class MyListener extends OurListener with EventListener {
      def listen(event: Event): String = {
        event match {
          case Event("Woodchuck Stampede") ⇒
            "An unfortunate woodchuck stampede occurred"
          case _ ⇒ "Nothing of importance occurred"
        }
      }
    }

    val evt = Event("Woodchuck Stampede")
    val myListener = new MyListener
    myListener.listen(evt) should be("An unfortunate woodchuck stampede occurred")
  }
  "Traits are polymorphic. Any type can be referred to by another type if related by extension" should "be" in {
    case class Event(name: String)

    trait EventListener {
      def listen(event: Event): String
    }

    class MyListener extends EventListener {
      def listen(event: Event): String = {
        event match {
          case Event("Moose Stampede") ⇒
            "An unfortunate moose stampede occurred"
          case _ ⇒ "Nothing of importance occurred"
        }
      }
    }

    val myListener = new MyListener

    myListener.isInstanceOf[MyListener] should be(true)
    myListener.isInstanceOf[EventListener] should be(true)
    myListener.isInstanceOf[Any] should be(true)
    myListener.isInstanceOf[AnyRef] should be(true)
  }
  "dependencies can't have identical method/property names or else you'll get an illegal inheritance error" should "be" in {
    trait B {
      def bId = 2
    }

    trait A { self: B =>
      def aId = 1
    }

    //val a = new A  //***does not compile!!!***
    val obj = new A with B
    (obj.aId + obj.bId) should be(3)
  }
}
