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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

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
  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

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
  override def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))
  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(source: Int): Int = source * 2
  }).toString)
  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(source: Int): Boolean = source % 2 == 0
  }).toString)
  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(source: Int): MyList[Int] = new Cons(source, new Cons(source + 1, Empty))
  }).toString)
  println(cloneListOfIntegers == listOfIntegers)
}

trait MyPredicate[-T] {
  def test(source: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(source: A): B
}
