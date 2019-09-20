package example.variances

class GParent

class Parent extends GParent

class Child extends Parent

class Box[+A]

class Box2[-A]

class Box3[A]

object ParentTest extends App {
  def foo(x: Box[Parent]): Box[Parent] = identity(x)

  def bar(x: Box2[Parent]): Box2[Parent] = identity(x)

  def bar(x: Box3[Parent]): Box3[Parent] = identity(x)

//  foo(new Box[GParent]) // type error
  foo(new Box[Parent]) // type error
  foo(new Box[Child]) // success

  bar(new Box2[GParent]) // success
  bar(new Box2[Parent]) // success
//  bar(new Box2[Child]) // type error

//  bar(new Box3[GParent]) // type error
  bar(new Box3[Parent]) // success
//  bar(new Box3[Child]) // type error

}
