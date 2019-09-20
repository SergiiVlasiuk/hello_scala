import reactivemongo.api.collections.bson.BSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try
import reactivemongo.api.{Cursor, DefaultDB, MongoConnection, MongoDriver}
import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros, document}

object GetStartedWithMongo {
  def main(args: Array[String]): Unit = {
    val age = 95
    val person: Person = Person("firstName", "lastName", age)
    createPerson(person)
    findPersonByAge(age).andThen {
      case x => println(s"loaded data: $x")
    }

    println("completed")
  }

  val database = "personDb"
  val driver = new MongoDriver
  val mongoUri = "mongodb://localhost:27017/mydb?authMode=scram-sha1"
  // Connect to the database: Must be done only once per application
  val parsedUri: Try[MongoConnection.ParsedURI] =
    MongoConnection.parseURI(mongoUri)
  val connection: Try[MongoConnection] = parsedUri.map(driver.connection(_))
  val futureConnection: Future[MongoConnection] = Future.fromTry(connection)

  //  val db = connection(database)
  val db: Future[DefaultDB] = futureConnection.flatMap(_.database(database))

  //  def personCollection: Future[List[Person]] = db.map(_.collection("person"))
  def personCollection: Future[BSONCollection] = db.map(_.collection("person"))

  // use personWriter
  def createPerson(person: Person): Future[Unit] =
    personCollection.flatMap(_.insert.one(person).map(_ => {}))

  // Write Documents: insert or update

  implicit def personWriter: BSONDocumentWriter[Person] = Macros.writer[Person]
  // or provide a custom one

  def updatePerson(person: Person): Future[Int] = {
    val selector =
      document("firstName" -> person.firstName, "lastName" -> person.lastName)

    // Update the matching person
    personCollection.flatMap(_.update.one(selector, person).map(_.n))
  }

  implicit def personReader: BSONDocumentReader[Person] = Macros.reader[Person]
  // or provide a custom one

  def findPersonByAge(age: Int): Future[List[Person]] =
    personCollection.flatMap(
      _.find(document("age" -> age)). // query builder
      cursor[Person](). // using the result cursor
      collect[List](-1, Cursor.FailOnError[List[Person]]())
    )
  // ... deserializes the document using personReader

  // Custom persistent types
  case class Person(firstName: String, lastName: String, age: Int)
}

