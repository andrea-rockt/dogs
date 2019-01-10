package dogs

import simulacrum.{op, typeclass}

@typeclass trait Apply[F[_]] {
  @op("<*>") def ap[A, B](ff: F[A => B])(f: F[A]): F[B]
}
