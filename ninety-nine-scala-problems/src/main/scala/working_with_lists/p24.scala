package working_with_lists
// P24 (*) Lotto: Draw N different random numbers from the set 1..M.
//     Example:
//     scala> lotto(6, 49)
//     res0: List[Int] = List(23, 1, 17, 33, 21, 37)

object p24 {
  import working_with_lists.p23.randomSelect
  def lotto(count: Int, max: Int): List[Int] = 
    randomSelect(count, List.range(1, max + 1))
}
