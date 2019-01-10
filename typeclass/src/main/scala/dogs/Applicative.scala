package dogs

import simulacrum.typeclass

@typeclass trait Applicative[F[_]] extends Functor[F] with Apply[F] {
  def pure[A](a: A): F[A]

  override def map[A, B](fa: F[A])(f: A => B): F[B] =
    ap(pure(f))(fa)


}
