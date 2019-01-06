package dogs.laws

import dogs.Semigroup

trait SemigroupLaws[A] {
  implicit def S: Semigroup[A]

  def semigroupAssociative(x: A, y: A, z: A): IsEq[A] =
    S.combine(S.combine(x, y), z) <-> S.combine(x, S.combine(y, z))

}

object SemigroupLaws {
  def apply[A](implicit ev: Semigroup[A]): SemigroupLaws[A] =
    new SemigroupLaws[A] { def S: Semigroup[A] = ev }
}