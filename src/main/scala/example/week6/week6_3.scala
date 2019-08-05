package example.week6

object week6_3 extends App {
  def queens(n: Int) = {
    def placeQueens(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens
    }
    placeQueens(n)
  }
  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    val queensWithRow = (row - 1 to 0 by -1) zip queens
    queensWithRow forall {
      case (r, c) => col != c  && math.abs(col - c) != row - r
    }
  }

  def showAll(queens:Set[List[Int]])={
    queens.map(show).foreach(println)
  }
  def show(queens:List[Int])={
    val lines =
      for (col <- queens.reverse)
        yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    "\n" + (lines mkString "\n")
  }

  println(queens(2))
  println(queens(3))
  println(queens(4))
  showAll(queens(4))
  println(queens(5).size)
  println(queens(5))
  showAll(queens(5))
  println(queens(6).size)
  println(queens(6))
  showAll(queens(6))
  println(queens(7).size)
  println(queens(7))
  showAll(queens(7))

}
