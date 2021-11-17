package example.mixin

class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0
  def hasNext: Boolean = i < s.length
  def next(): Char = {
    val ch = s charAt i
    i += 1
    ch
  }
}
