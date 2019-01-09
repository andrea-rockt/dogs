package dogs.laws

import dogs.{Applicative, FlatMap, Monad}

trait MonadLaws[F[_]] extends ApplicativeLaws[F]
                         with Applicative.ToApplicativeOps
                         with FlatMap.ToFlatMapOps {

  implicit def F: Monad[F]

  def monadLeftIdentity[A, B](a: A, f: A => F[B]): IsEq[F[B]] =
    F.pure(a).flatMap(f) <-> f(a)

  def monadRightIdentity[A](fa: F[A]): IsEq[F[A]] =
    fa.flatMap(F.pure) <-> fa

  def monadAssociativity[A, B, C](fa: F[A], f: A => F[B], g: B => F[C]) : IsEq[F[C]] =
    fa.flatMap(f).flatMap(g) <-> fa.flatMap(x => f(x).flatMap(g))
}

object MonadLaws {
  def apply[F[_]](implicit ev: Monad[F]): MonadLaws[F] =
    new MonadLaws[F] {
      def F: Monad[F] = ev
    }
}
