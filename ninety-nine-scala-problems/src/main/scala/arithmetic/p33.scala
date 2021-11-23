package arithmetic

// P33 (*) Determine whether two positive integer numbers are coprime.
//     Two numbers are coprime if their greatest common divisor equals 1.
//
//     scala> 35.isCoprimeTo(64)
//     res0: Boolean = true

object p33 {

  implicit class impP33(val start: Int) {
    def isCoprimeTo(n: Int): Boolean = p32.gcd(start, n) == 1
  }
}
