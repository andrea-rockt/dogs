package dogs.laws

import dogs.Monoid

trait MonoidLaws[A] extends SemigroupLaws[A] {
  implicit override def S: Monoid[A]

  def monoidLeftIdentity(x: A): IsEq[A] =
    S.combine(S.empty, x) <-> x


  def monoidRightIdentity(x: A): IsEq[A] =
    S.combine(x, S.empty) <-> x

}


object MonoidLaws {
  def apply[A](implicit ev: Monoid[A]): MonoidLaws[A] =
    new MonoidLaws[A] { def S: Monoid[A] = ev }
}