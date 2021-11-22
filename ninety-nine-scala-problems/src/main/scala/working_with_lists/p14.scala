package working_with_lists
// P14 (*) Duplicate the elements of a list.
//     Example:
//     scala> duplicate(List('a, 'b, 'c, 'c, 'd))
//     res0: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

object p14 {
  def duplicate[A](ls: List[A]): List[A] = ls flatMap { e => List(e, e) }
}
