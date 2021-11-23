package arithmetic

import arithmetic.p33._

import scala.annotation.tailrec
// P34 (**) Calculate Euler's totient function phi(m).
//     Euler's so-called totient function phi(m) is defined as the number of
//     positive integers r (1 <= r < m) that are coprime to m.  As a special
//     case, phi(1) is defined to be 1.
//
//     scala> 10.totient
//     res0: Int = 4
import scala.language.postfixOps

object p34 {

  implicit class Totient(val start: Int) {
    def totient: Int = (1 to start) filter { start.isCoprimeTo(_) } length

    def totient2: Int = (1 to start).count(start.isCoprimeTo(_))
  }
}
