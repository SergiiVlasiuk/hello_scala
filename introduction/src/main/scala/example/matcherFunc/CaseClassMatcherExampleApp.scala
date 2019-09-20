package example.matcherFunc

object CaseClassMatcherExampleApp {
  def matcherFunc(acc: Account): String =
    acc match {
      case usr @ User("dave", email) =>
        println(s"This is dave, his email: $email")
        s"first case: This is dave, his email: $email"
      case usr @ User(name, email) if email == "" =>
        println("User " + name + " has no email")
        s"second case: User is $name, his email: $email"
      case usr @ User(name, email) =>
        println("User " + name + " has email " + email)
        s"third case: User is $name, his email: $email"
      case su: SuperUser =>
        println("It`s a superuser")
        s"fourth case: SuperUser is ${su.name} , his email: ${su.email}, privelegies: ${su.privileges}"
      case smth =>
        println("We don’t know what is it")
        s"fifth case: We don’t know what is it: $smth"
    }
}

trait Account

case class User(name: String, email: String) extends Account

case class SuperUser(name: String, email: String, privileges: String)
    extends Account

case object SomethingStrange extends Account
