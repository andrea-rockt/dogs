package dogs

import simulacrum.typeclass

@typeclass trait Functor[F[_]] {
  self =>

  def map[A, B](fa: F[A])(f: A => B): F[B]

  def compose[G[_] : Functor] : Functor[λ[A => F[G[A]]]] = new Functor[λ[A => F[G[A]]]] {
    override def map[A, B](fga: F[G[A]])(f: A => B): F[G[B]] = {

      self.map(fga) { ga =>
        Functor[G].map(ga) { a =>
          f(a)
        }
      }
    }

  }
}