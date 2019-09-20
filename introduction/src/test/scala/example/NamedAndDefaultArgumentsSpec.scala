package example

import org.scalatest._

class NamedAndDefaultArgumentsSpec extends FlatSpec with Matchers {
  "Can specify arguments in any order if you use their names" should "be" in {
    val me = new WithoutClassParameters()

    // what happens if you change the order of these parameters (nothing)
    val myColor = me.addColors(green = 0, red = 255, blue = 0)

    // for koan, remove the values in the should equal
    myColor should equal((255, 0, 0))
  }
  "Can default arguments if you leave them off" should "be" in {
    val me = new WithoutClassParameters()
    val myColor = me.addColorsWithDefaults(green = 255)

    myColor should equal(0, 255, 0)
  }
  "Can access class parameters and specify arguments in any order if you use their names" should "be" in {
    val me = new WithClassParameters(40, 50, 60)
    val myColor = me.addColors(green = 50, red = 60, blue = 40)

    myColor should equal(100, 100, 100)
  }
  "Can access class parameters and default arguments if you leave them off" should "be" in {
    val me = new WithClassParameters(10, 20, 30)
    val myColor = me.addColorsWithDefaults(green = 70)

    myColor should equal(10, 90, 30)
  }
  "Can default class parameters and have default arguments too" should "be" in {
    val me = new WithClassParametersInClassDefinition()
    val myColor = me.addColorsWithDefaults(green = 70)

    myColor should equal(0, 325, 100)
  }
  "Default parameters can be functional too" should "be" in {
    def reduce(a: Int, f: (Int, Int) ⇒ Int = _ + _): Int = f(a, a)
    reduce(5) should equal(10)
    reduce(5, _ * _) should equal(25)
  }
}

/*====================================================================================================================*/
class WithoutClassParameters() {
  def addColors(red: Int, green: Int, blue: Int) = {
    (red, green, blue)
  }

  def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
    (red, green, blue)
  }
}

class WithClassParameters(val defaultRed: Int,
                          val defaultGreen: Int,
                          val defaultBlue: Int) {
  def addColors(red: Int, green: Int, blue: Int) = {
    (red + defaultRed, green + defaultGreen, blue + defaultBlue)
  }

  def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
    (red + defaultRed, green + defaultGreen, blue + defaultBlue)
  }
}

class WithClassParametersInClassDefinition(val defaultRed: Int = 0,
                                           val defaultGreen: Int = 255,
                                           val defaultBlue: Int = 100) {
  def addColors(red: Int, green: Int, blue: Int) = {
    (red + defaultRed, green + defaultGreen, blue + defaultBlue)
  }

  def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
    (red + defaultRed, green + defaultGreen, blue + defaultBlue)
  }
}
