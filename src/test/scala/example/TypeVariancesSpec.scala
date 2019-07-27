package example

import org.scalatest._

class TypeVariancesSpec extends FlatSpec with Matchers {
  "Using type inference the type that you instantiate will be the val or var reference type" should "be" in {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket = new MyContainer(new Orange())
    fruitBasket.contents should be("Orange")
  }
  "You can explicitly declare the type variable of the object during instantiation" should "be" in {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket = new MyContainer[Fruit](new Orange())
    fruitBasket.contents should be("Fruit")
  }
  "You can coerce your object to a type" should "be" in {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer(new Orange())
    fruitBasket.contents should be("Fruit")
  }
  "how do we get to set a Fruit basket to an Orange basket? You make it covariant using +" should "be" in {
    class MyContainer[+A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
    fruitBasket.contents should be("Orange")
  }
  "problem with covariance is that you can't mutate" should "be" in {
    // set or change the object since it has to guarantee that what you put into it is a valid type. In other words the
    //reference is a fruit basket, but we still have to make sure that no other fruit can be placed in our orange basket:
    class MyContainer[+A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }
    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
    fruitBasket.contents should be("Orange")
    class NavelOrange extends Orange //Creating a subtype to prove a point
//    val navelOrangeBasket: MyContainer[NavelOrange] = new MyContainer[Orange](new Orange()) //Bad!
//    val tangeloBasket: MyContainer[Tangelo] = new MyContainer[Orange](new Orange()) //Bad!
  }
  "contravariance - you can apply any container with a certain type to a container with a superclass of that type" should "be" in {
    class MyContainer[-A](a: A)(implicit manifest: scala.reflect.Manifest[A]) { //Can't receive a val because it would be in a covariant position
      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be("Citrus")
    val orangeBasket: MyContainer[Orange] =
      new MyContainer[Citrus](new Tangelo)
    orangeBasket.contents should be("Citrus")
    val tangeloBasket: MyContainer[Tangelo] =
      new MyContainer[Citrus](new Orange)
    tangeloBasket.contents should be("Citrus")
    val bananaBasket: MyContainer[Banana] = new MyContainer[Fruit](new Apple)
    bananaBasket.contents should be("Fruit")
  }
  "Invariance means you need to specify the type exactly" should "be" in {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be("Citrus")
  }
}

/* ================================================================================================================== */
class Fruit()

class Orange() extends Citrus
class Tangelo() extends Citrus
class Citrus() extends Fruit
class Banana() extends Fruit
class Apple() extends Fruit

