package exercises

abstract class MyList[+A] {

  /*
    contains Int
    head - first element of the list
    tail - remainder of the list
    isEmpty - is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](e: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  // higher order functions
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  /*
  1. Generic trait MyPredicate[T]
    - test(T)
  2. Generic trait MyTransformer[A, B]
    - convert A to B
  3. MyList:
      - map(transformer) => MyList
      - filter(predicate) => MyList
      - flatMap(transformer from A to MyList[B]) => MyList[B]
 */
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, Empty)
  override def printElements: String = ""
  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](e: B): MyList[B] = new Cons(e, this)
  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  override def map[B](transformer: A => B): MyList[B] = Cons(transformer(h), t.map(transformer))
  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.map(source => source * 2).toString)
  println(listOfIntegers.filter(source => source % 2 == 0).toString)
  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(source => new Cons(source, new Cons(source + 1, Empty))).toString)
  println(cloneListOfIntegers == listOfIntegers)
}
