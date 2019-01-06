package dogs

import simulacrum.{op, typeclass}

@typeclass trait Eq[A] {

  @op("===") def eqv(x: A, y: A): Boolean

  @op("!==") def neqv(x: A, y: A): Boolean = !eqv(x, y)

}

object Eq {

  def allEqual[A]: Eq[A] = (_: A, _: A) => true

  def fromUniversalEquality[A]: Eq[A] = _ == _

  implicit def intEq: Eq[Int] = fromUniversalEquality[Int]

  implicit def doubleEq: Eq[Double] = fromUniversalEquality[Double]

  implicit def floatEq: Eq[Float] = fromUniversalEquality[Float]

  implicit def longEq: Eq[Long] = fromUniversalEquality[Long]

  implicit def shortEq: Eq[Short] = fromUniversalEquality[Short]

  implicit def byteEq: Eq[Byte] = fromUniversalEquality[Byte]

  implicit def stringEq: Eq[String] = fromUniversalEquality[String]

}

