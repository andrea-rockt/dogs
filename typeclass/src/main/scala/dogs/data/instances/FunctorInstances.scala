package dogs.data.instances

import dogs.Functor
import dogs.data.{Id, Writer}

trait FunctorInstances {

  implicit def writerFunctor[L]: Functor[Writer[L, ?]] = new Functor[Writer[L, ?]] {
    override def map[A, B](fa: Writer[L, A])(f: A => B): Writer[L, B] = fa match {
      case Writer((log, a)) =>
        Writer[L, B]((log, f(a)))
    }
  }

  implicit val idFunctor: Functor[Id] = new Functor[Id] {
    override def map[A, B](fa: Id[A])(f: A => B): Id[B] = f(fa)
  }

}

object FunctorInstances extends FunctorInstances
