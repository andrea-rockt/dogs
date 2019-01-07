package dogs.instances

import dogs.{Monoid, Semigroup}

trait MonoidInstances {

  implicit def intMonoid(implicit S: Semigroup[Int]): Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0

    override def combine(x: Int, y: Int): Int = S.combine(x, y)
  }

  implicit def doubleMonoid(implicit S: Semigroup[Double]): Monoid[Double] = new Monoid[Double] {
    override def empty: Double = 0.0

    override def combine(x: Double, y: Double): Double = S.combine(x, y)
  }

  implicit def listMonoid[A](implicit S: Semigroup[List[A]]): Monoid[List[A]] = new Monoid[List[A]] {
    override def empty: List[A] = List()

    override def combine(x: List[A], y: List[A]): List[A] = S.combine(x, y)
  }

  implicit def stringMonoid(implicit S: Semigroup[String]): Monoid[String] = new Monoid[String] {
    override def empty: String = ""

    override def combine(x: String, y: String): String = S.combine(x, y)
  }
}

object MonoidInstances extends MonoidInstances
