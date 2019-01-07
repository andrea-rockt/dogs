package dogs.instances

import dogs.Eq

trait EqInstances {
  implicit def listEq[A]: Eq[List[A]] = Eq.fromUniversalEquality[List[A]]

  implicit def vectorEq[A]: Eq[Vector[A]] = Eq.fromUniversalEquality[Vector[A]]

  implicit def optionEq[A]: Eq[Option[A]] = Eq.fromUniversalEquality[Option[A]]

  implicit def intEq: Eq[Int] = Eq.fromUniversalEquality[Int]

  implicit def doubleEq: Eq[Double] = Eq.fromUniversalEquality[Double]

  implicit def floatEq: Eq[Float] = Eq.fromUniversalEquality[Float]

  implicit def longEq: Eq[Long] = Eq.fromUniversalEquality[Long]

  implicit def shortEq: Eq[Short] = Eq.fromUniversalEquality[Short]

  implicit def byteEq: Eq[Byte] = Eq.fromUniversalEquality[Byte]

  implicit def stringEq: Eq[String] = Eq.fromUniversalEquality[String]
}

object EqInstances extends EqInstances
