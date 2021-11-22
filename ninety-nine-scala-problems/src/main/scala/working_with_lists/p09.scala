package working_with_lists
// P09 (**) Pack consecutive duplicates of list elements into sublists.
//     If a list contains repeated elements they should be placed in separate
//     sublists.
//
//     Example:
//     scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//     res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

object p09 {
  def pack[A](ls: List[A]): List[List[A]] = {
    if (ls.isEmpty) List(List())
    else {
      /* Splits this $coll into a prefix/suffix pair according to a predicate.
       *  Note: `c span p`  is equivalent to (but possibly more efficient than)
       *  `(c takeWhile p, c dropWhile p)`, provided the evaluation of the
       *  predicate `p` does not cause any side-effects.
       *  $orderDependent
       */
      val (packed, next) = ls span { _ == ls.head }
      println(s"packed: $packed, next: $next")
      if (next == Nil) List(packed)
      else packed :: pack(next)
    }
  }
}
