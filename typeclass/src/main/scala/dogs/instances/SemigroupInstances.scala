package dogs.instances

import dogs.Semigroup

trait SemigroupInstances {

  implicit val intSemigroup: Semigroup[Int] = (x: Int, y: Int) => x + y

  implicit val doubleSemigroup: Semigroup[Double] = (x: Double, y: Double) => x + y

  implicit def listSemigroup[A]: Semigroup[List[A]] = (x: List[A], y: List[A]) => x ::: y

  implicit val stringSemigroup: Semigroup[String] = (x: String, y: String) => x + y

}

object SemigroupInstances extends SemigroupInstances