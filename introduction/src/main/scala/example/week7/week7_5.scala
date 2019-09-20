package example.week7

object week7_5 extends App {
  println("scala" drop 1) // > res_: String = cala

  var problem = new Pouring(Vector(4, 7, 19)) //> problem  : example.week7.Pouring = example.week7.Pouring@54a097cc
  problem.moves //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with we
  //| ek7.test.problem.Move] = Vector(Empty(0), Empty(1), Empty(2), Fill(0), Fill(1
  //| ), Fill(2), Pour(0,1), Pour(0,2), Pour(1,0), Pour(1,2), Pour(2,0), Pour(2,1))
  //|
  problem.solutions(17) //> res1: Stream[example.week7.test.problem.Path] = Stream(Fill(2) Pour(2,1) Pour(2,1) F
  //| ill(2) Pour(2,1) Pour(0,1) --> Vector(7, 17, 7), ?)

  println(Vector(4, 7, 19)(1))
  println(problem)
  println(problem.moves)
  println(problem.solutions(17))
  println(problem.solutions(6))

  problem = new Pouring(Vector(4, 7))
  println(problem)
  println(problem.moves)
  println(problem.solutions(6))

  problem = new Pouring(Vector(4, 9))
  println(problem)
  println(problem.moves)
  println(problem.solutions(6))

}

class Pouring(capacity: Vector[Int]) {

  // States
  type State = Vector[Int]
  val initialState = capacity map (x => 0)

  // Moves
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    def change(state: State) = state updated (glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    def change(state: State) = state updated (glass, capacity(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) = {
      val amount = state(from) - math.min(capacity(to), state(to))
      state updated (from, state(from) - amount) updated (to, state(to) + amount)
    }
  }

  val glasses = 0 until capacity.length
  val moves =
    (for (g <- glasses) yield Empty(g)) ++
      (for (g <- glasses) yield Fill(g)) ++
      (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  // Paths
  class Path(history: List[Move], val endState: State) {
    def extend(move: Move) = new Path(move :: history, move change endState)

    override def toString = (history.reverse mkString " ") + " --> " + endState
  }

  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  val pathSets = from(Set(initialPath), Set(initialState))

  def solutions(target: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
}
