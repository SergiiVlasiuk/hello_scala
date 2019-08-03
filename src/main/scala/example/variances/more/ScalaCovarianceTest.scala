package example.variances.more


class Animal[+T](val animial: T)

class Dog

class Puppy extends Dog

class AnimalCarer(val dog: Animal[Dog])

object ScalaCovarianceTest {
  def main(args: Array[String]) {
    val puppy = new Puppy
    val dog = new Dog

    val puppyAnimal: Animal[Puppy] = new Animal[Puppy](puppy)
    val dogAnimal: Animal[Dog] = new Animal[Dog](dog)

    val dogCarer = new AnimalCarer(dogAnimal)
    val puppyCarer = new AnimalCarer(puppyAnimal)

    println("Done.")
  }
}
