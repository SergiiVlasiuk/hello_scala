package example.week6

object week6_6 extends App {

  def showCapital(country: String) = capitalOfCountry.get(country) match {
    case Some(capital) => capital
    case None => "missing data"
  }

  val romanNumbers = Map("I" -> 1, "V" -> 5, "X" -> 10) // Map[String, Int]
  val capitalOfCountry = Map("US" -> "Washington DC", "Switzerland" -> "Bern") // Map[String, String]

  println(capitalOfCountry("US")) // "Washington DC"
  //  println (capitalOfCountry("Andorra"))  // java.util.NoSuchElementException: key not found: Andorra

  println(showCapital("US")) // "Washington DC"
  println(showCapital("Andorra")) // java.util.NoSuchElementException: key not found: Andorra


  println("Poly ====================================================================================================")
  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)) // 6.2x^5 + 4.0x^3 + 2.0x^1
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0)) // 7.0x^3 + 3.0x^0
  println(p1)
  println(p2)
  println(p1 + p2)
  println(p2 + p1)
  println("Poly2 ====================================================================================================")
  val p12 = new Poly2(1 -> 2.0, 3 -> 4.0, 5 -> 6.2) // 6.2x^5 + 4.0x^3 + 2.0x^1
  val p22 = new Poly2(Map(0 -> 3.0, 3 -> 7.0)) // 7.0x^3 + 3.0x^0
  println(p12)
  println(p22)
  println(p22.terms(3))
  println(p22.terms(11))
  println(p12 + p22)
  println(p22 + p12)

  println("Poly3 - foldLeft for sum solution =====================================================================")
  val p13 = new Poly3(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)) // 6.2x^5 + 4.0x^3 + 2.0x^1
  val p23 = new Poly3(Map(0 -> 3.0, 3 -> 7.0)) // 7.0x^3 + 3.0x^0
  println(p13)
  println(p23)
  println(p13 + p23)
  println(p23 + p13)

}

class Poly(val terms: Map[Int, Double]) {
  //  def + (other: Poly) = new Poly(terms ++ other.terms) // wrong way
  def +(other: Poly) = new Poly(terms ++ other.terms map adjust) // solution
  def adjust(term: (Int, Double)): (Int, Double) = {
    val (exp, coeff) = term
    terms get exp match {
      case Some(coeff1) => exp -> (coeff + coeff1)
      case None => exp -> coeff
    }
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
}

class Poly2(val terms0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0

  //  def + (other: Poly2) = new Poly(terms ++ other.terms) // wrong way
  def +(other: Poly2) = new Poly(terms ++ other.terms map adjust) // solution
  def adjust(term: (Int, Double)): (Int, Double) = {
    val (exp, coeff) = term
    exp -> (coeff + terms(exp))
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
}
class Poly3(val terms0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0

  def +(other: Poly3) = new Poly((terms foldLeft other.terms)(addTerms)) // solution
  def addTerms(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coeff) = term
    terms + (exp -> (coeff + terms(exp)))
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
}
