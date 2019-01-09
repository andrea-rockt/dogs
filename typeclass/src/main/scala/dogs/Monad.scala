package dogs

trait Monad[F[_]] extends Applicative[F] with FlatMap[F]

object Monad {
  def apply[F[_] : Applicative :FlatMap] : Monad[F] = new Monad[F] {
    override def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B] = FlatMap[F].flatMap(fa)(f)

    override def pure[A](a: A): F[A] = Applicative[F].pure(a)

    override def ap[A, B](ff: F[A => B])(f: F[A]): F[B] = Applicative[F].ap(ff)(f)
  }
}