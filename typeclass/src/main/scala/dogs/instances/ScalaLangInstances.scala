package dogs.instances

import dogs.{Eq, Monoid}

trait ScalaLangInstances {

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0

    override def combine(x: Int, y: Int): Int = x + y
  }

  implicit val doubleMonoid: Monoid[Double] = new Monoid[Double] {
    override def empty: Double = 0.0

    override def combine(x: Double, y: Double): Double = x + y
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def empty: String = ""

    override def combine(x: String, y: String): String = x ++ y
  }

  implicit def intEq: Eq[Int] = Eq.fromUniversalEquality[Int]

  implicit def doubleEq: Eq[Double] = Eq.fromUniversalEquality[Double]

  implicit def floatEq: Eq[Float] = Eq.fromUniversalEquality[Float]

  implicit def longEq: Eq[Long] = Eq.fromUniversalEquality[Long]

  implicit def shortEq: Eq[Short] = Eq.fromUniversalEquality[Short]

  implicit def byteEq: Eq[Byte] = Eq.fromUniversalEquality[Byte]

  implicit def stringEq: Eq[String] = Eq.fromUniversalEquality[String]
}

object ScalaLangInstances extends ScalaLangInstances
