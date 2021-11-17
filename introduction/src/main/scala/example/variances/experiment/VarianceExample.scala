package example.variances.experiment

/*
Variance:
+T/T/-T covariance/invariance/contravariance

Wrapper[Dog]
Wrapper[Domestic]
no relations means invariance (because of wrapper)

Вариантность — это сохранение совместимости присваивания исходных типов у производных типов.
Ковариантность (covariance) — это сохранение совместимости присваивания исходных типов у производных в прямом порядке.
Контравариантность (contravariance) — это сохранение совместимости присваивания исходных типов у производных в обратном порядке.
Инвариантность (invariance) — ситуация, когда наследование исходных типов не переносится на производные.

param is contravariant position (position consumes contravariant type)
*/
object VarianceExample {

  def main(args: Array[String]): Unit = {

    val w = new VarianceWrapper[String]("hi variance")
    println(w)
    println(w.map(_.length))

    println("/=== max results ===/")
    val five = VarianceWrapper(5)
    println(five.max(4))
    println(five.max(5))
    println(five.max(6))
  }
}

case class VarianceWrapper[+T : Ordering](x: T){
  def map[R : Ordering](fn: T => R): VarianceWrapper[R] = VarianceWrapper(fn(x))

  def max[R >: T : Ordering](maybeMax: R): VarianceWrapper[R] = {
    val ordering: Ordering[R] = implicitly[Ordering[R]]
    //    if (ordering.compare(x, maybeMax) >= 0) this.map(_.asInstanceOf[R]) // huck,
    if (ordering.compare(x, maybeMax) >= 0) this
    else VarianceWrapper(maybeMax)
  }
//  def self(param: T): T = param
}

case class MinMaxContainer[+T : Ordering](x: T) {

  def replaceIfSmaller[R >: T : Ordering](maybeSmaller: R): MinMaxContainer[R] =
    if (isGreater(maybeSmaller)) MinMaxContainer(maybeSmaller)
    else this

  def replaceIfBigger[R >: T : Ordering](maybeBigger: R): MinMaxContainer[R] =
    if (!isGreater(maybeBigger)) MinMaxContainer(maybeBigger)
    else this

  private def isGreater[R >: T : Ordering](y: R):Boolean = implicitly[Ordering[R]].compare(x, y) >= 0
}

sealed class Animal

class Domestic extends Animal
class Dog extends Domestic
class Cat extends Animal

class Wild extends Animal
class Tiger extends Wild
class Lion extends Wild
