package example.mixin

trait RichIterator extends AbsIterator {

  def foreach(f: T => Unit): Unit = while (hasNext) f(next())
}
