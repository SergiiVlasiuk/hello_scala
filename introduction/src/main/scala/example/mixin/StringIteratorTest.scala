package example.mixin

object StringIteratorTest extends App {
  val richStringIter = new RichStringIter
  richStringIter foreach println
}

class RichStringIter extends StringIterator("Scala") with RichIterator
