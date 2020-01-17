package lectures.part2oop

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  // class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // sensible toString
  println(jim)

  // equals and hashcode implemented
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // has companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // serializable
  // have extractor patterns - can be used in pattern matching

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}
