package dogs.data.instances

import dogs.Monad
import dogs.data.State

trait StateInstances {

  implicit def stateMonad[S]: Monad[State[S, ?]] = new Monad[State[S, ?]] {

    override def flatMap[A, B](fa: State[S, A])(f: A => State[S, B]): State[S, B] = State[S, B] { s0 =>
        fa.run(s0) match {
          case (s1, a) =>
            f(a).run(s1)
        }
    }


    override def pure[A](a: A): State[S, A] = State[S, A](s => (s, a))

    override def ap[A, B](fab: State[S, A => B])(fa: State[S, A]): State[S, B] = State[S, B] {
      s =>
        fab.run(s) match {
          case (s1, f) =>
            fa.run(s1) match {
              case (s2, a) => (s2, f(a))
            }
        }
    }

  }
}
