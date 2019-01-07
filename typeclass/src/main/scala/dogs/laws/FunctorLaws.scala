package dogs.laws

import dogs.Functor

trait FunctorLaws[F[_]] extends Functor.ToFunctorOps{
  implicit def F: Functor[F]

  def functorIdentity[A](x: F[A]): IsEq[F[A]] =
    x.map(identity) <-> x

  def functorComposition[A, B, C](x: F[A], f: A => B, g: B => C): IsEq[F[C]] =
    x.map(f).map(g) <-> x.map(g.compose(f))

}


object FunctorLaws {
  def apply[F[_]](implicit ev: Functor[F]): FunctorLaws[F] =
    new FunctorLaws[F] {
      def F: Functor[F] = ev
    }
}