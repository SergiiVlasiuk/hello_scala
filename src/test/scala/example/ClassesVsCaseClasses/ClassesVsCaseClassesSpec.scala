package example.ClassesVsCaseClasses

import org.scalatest._

class ClassesVsCaseClassesSpec extends FlatSpec with Matchers {
  "Classes Vs Case Classes" should "be" in {
    class Note(_name: String, _duration: String, _octave: Int) extends Serializable {

      // Constructor parameters are promoted to members
      val name = _name
      val duration = _duration
      val octave = _octave

      // Equality redefinition
      override def equals(other: Any): Boolean = other match {
        case that: Note =>
          (that canEqual this) &&
            name == that.name &&
            duration == that.duration &&
            octave == that.octave
        case _ => false
      }

      def canEqual(other: Any): Boolean = other.isInstanceOf[Note]

      // Java hashCode redefinition according to equality
      override def hashCode(): Int = {
        val state = Seq(name, duration, octave)
        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
      }

      // toString redefinition to return the value of an instance instead of its memory addres
      override def toString = s"Note($name,$duration,$octave)"

      // Create a copy of a case class, with potentially modified field values
      def copy(name: String = name, duration: String = duration, octave: Int = octave): Note =
        new Note(name, duration, octave)

    }

    object Note {

      // Constructor that allows the omission of the `new` keyword
      def apply(name: String, duration: String, octave: Int): Note =
        new Note(name, duration, octave)

      // Extractor for pattern matching
      def unapply(note: Note): Option[(String, String, Int)] =
        if (note eq null) None
        else Some((note.name, note.duration, note.octave))

    }

    val c3 = Note("C", "Quarter", 3)
    c3.toString shouldBe "Note(C,Quarter,3)"
    val d = Note("D", "Quarter", 3)
    c3.equals(d) shouldBe false
    val c4 = c3.copy(octave = 4)
    c4.toString shouldBe "Note(C,Quarter,4)"
  }
}
