package example.variances.invariance

import example.variances.{Animal, Cat, Dog}

class Container[A](value: A) {
  private var _value: A = value
  def getValue: A = _value
  def setValue(value: A): Unit = {
    _value = value
  }
}

object Container extends App {

  val catContainer: Container[Cat] = new Container(Cat("Felix"))
//  val animalContainer: Container[Animal] = catContainer // is not compiled
//  animalContainer.setValue(Dog("Spot"))
  val cat: Cat = catContainer.getValue // Oops, we'd end up with a Dog assigned to a Cat
}
