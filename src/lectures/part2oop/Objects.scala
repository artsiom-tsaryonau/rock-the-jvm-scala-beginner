package lectures.part2oop

object Objects extends App {
  object Person {
    // "static" / "class" level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }

  class Person(val name: String) {
    // instance level functionality
  }

  println(Person.N_EYES)
  println(Person.canFly)

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  val bobbie = Person(mary, john)
}
