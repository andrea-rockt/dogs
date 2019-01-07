package dogs

import simulacrum.{op, typeclass}

@typeclass trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]

  @op("<*>") def ap[A, B](ff: F[A => B])(f: F[A]): F[B]

  override def map[A, B](fa: F[A])(f: A => B): F[B] =
    ap(pure(f))(fa)


}
