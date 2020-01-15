package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n-1}")
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")

      result
    }
  }
  println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // tail recursion - use

    factorialHelper(n, 1)
  }
  println(anotherFactorial(10))
  println(anotherFactorial(5000))

  /*
    1. concatenate a string n times
    2. isPrime function tail recursive
    3. fibonacci function, tail recursive
   */
  def concatenateTailRec(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatenateTailRec(aString, n - 1, aString + accumulator)
  println(concatenateTailRec("hello", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRect(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRect(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailRect(n / 2, true)
  }
  println(isPrime(2003))
  println(isPrime(629))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailRec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailRec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailRec(2, 1, 1)
  }
  println(fibonacci(8))
}
