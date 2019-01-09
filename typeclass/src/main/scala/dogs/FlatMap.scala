package dogs

import simulacrum.typeclass

@typeclass trait FlatMap[F[_]] {
  def flatMap[A,B](fa: F[A])(f:A => F[B]) : F[B]
}
