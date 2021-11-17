package working_with_lists
// P03 (*) Find the Kth element of a list.
//     By convention, the first element in the list is element 0.
//
//     Example:
//     scala> nth(2, List(1, 1, 2, 3, 5, 8))
//     res0: Int = 2

object p03 {
  // Trivial with builtins.
  def nthBuiltin[A](n: Int, ls: List[A]): A =
    if (n >= 0) ls(n) // ls.apply(n)
    else throw new NoSuchElementException

  // Not that much harder without.
  @scala.annotation.tailrec
  def nthRecursive[A](n: Int, ls: List[A]): A = (n, ls) match {
    case (0, h :: _   ) =>
      println(s"case (0, h :: _   ) => n: $n; h: $h")
      h
    case (n, _ :: tail) =>
      println(s"case (n, _ :: tail) => n: $n; tail: $tail")
      nthRecursive(n - 1, tail)
    case (_, Nil      ) => throw new NoSuchElementException
  }
}
