package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package members are accesible by their simple names
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val cinderella = new Princess // full qualified class name

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use FQ name
  val date = new Date
  val sqlDate = new SqlDate(2018, 5, 4)

  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
