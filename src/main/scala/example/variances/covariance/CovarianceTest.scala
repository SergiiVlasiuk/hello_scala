package example.variances.covariance

import example.variances.{Animal, Cat, Dog}

object CovarianceTest extends App {
  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      println(animal.name)
    }
  }

  val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
  val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

  println("================================   cats   ========================================")
  printAnimalNames(cats)
  // Whiskers
  // Tom

  println("================================   dogs   ========================================")
  printAnimalNames(dogs)
  // Fido
  // Rex
}
