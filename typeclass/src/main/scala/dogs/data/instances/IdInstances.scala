package dogs.data.instances

import dogs.Monad
import dogs.data.Id

trait IdInstances {

  implicit val idMonad: Monad[Id] = new Monad[Id] {
    override def flatMap[A, B](fa: Id[A])(f: A => Id[B]): Id[B] = f(fa)

    override def pure[A](a: A): Id[A] = a

    override def ap[A, B](ff: Id[A => B])(f: Id[A]): Id[B] = ff(f)
  }
}
