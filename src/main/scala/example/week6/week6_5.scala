package example.week6

class Hero(val name: String, val age: Int)
case class Hero2(name: String, age: Int)

object week6_5 extends App {

  val heroes = List(new Hero("edmon", 44), new Hero("junior", 13), new Hero("GOD", 9999), new Hero("Truth", 999))
  private val heroNames: List[String] =
    for (hero <- heroes)
      yield hero.name

  println(heroNames)
  println("the same result is handled using map:")
  println(heroes.map(_.name))

  println("============================================ case class result ==============================================")
  val heroes2 = List(new Hero2("edmon2", 44), new Hero2("junior2", 13), new Hero2("GOD2", 9999), new Hero2("Truth2", 999))

  println(for (hero <- heroes2)
    yield hero.name)
  println("the same result is handled using map:")
  println(heroes2.map(_.name))

}
