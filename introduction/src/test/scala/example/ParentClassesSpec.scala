package example

import org.scalatest._

class ParentClassesSpec extends FlatSpec with Matchers {
  "Class hierarchy is linear, a class can only extend from one parent class" should "be" in {
    class Soldier(val firstName: String, val lastName: String) {}
    class Pilot(override val firstName: String,
                override val lastName: String,
                val squadron: Long)
        extends Soldier(firstName, lastName)
    val pilot = new Pilot("John", "Yossarian", 256)
    pilot.firstName should be("John")
    pilot.lastName should be("Yossarian")
  }
  "A class that extends from another is polymorphic" should "be" in {
    class Soldier(val firstName: String, val lastName: String) {}
    class Pilot(override val firstName: String,
                override val lastName: String,
                val squadron: Long)
        extends Soldier(firstName, lastName)
    val pilot = new Pilot("John", "Yossarian", 256)
    val soldier: Soldier = pilot
    soldier.firstName should be("John")
    soldier.lastName should be("Yossarian")
  }
  "A class can be placed inside an abstract class just like in Java" should "be" in {
    abstract class Soldier(val firstName: String, val lastName: String) {

      class Catch(val number: Long) {
        // nothing to do here.  Just observe that it compiles
      }

    }
    class Pilot(override val firstName: String,
                override val lastName: String,
                val squadron: Long)
        extends Soldier(firstName, lastName)
    val pilot = new Pilot("John", "Yossarian", 256)
    val catchNo = new pilot.Catch(22) //using the pilot instance's path, create an catch object for it.
    catchNo.number should be(22)
  }
}
