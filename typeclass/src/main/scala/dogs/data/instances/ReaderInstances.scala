package dogs.data.instances

import dogs.{Contravariant, Monad}
import dogs.data.Reader

trait ReaderInstances {

  implicit def readerMonad[R]: Monad[Reader[R, ?]] = new Monad[Reader[R, ?]] {
    override def pure[A](a: A): Reader[R, A] = Reader(_ => a)

    override def ap[A, B](fab: Reader[R, A => B])(fa: Reader[R, A]): Reader[R, B] =
      fab match {
        case Reader(f) => fa match {
          case Reader(a) =>
            Reader[R, B](r => f(r)(a(r)))
        }
      }

    override def flatMap[A, B](fa: Reader[R, A])(fab: A => Reader[R, B]): Reader[R, B] =
      fa match {
        case Reader(f) => Reader { r =>
          val a = f(r)
          fab(a) match {
            case Reader(g) =>
              g(r)
          }
        }
      }
  }

  implicit def readerContramap[C] : Contravariant[Reader[?, C]] = new Contravariant[Reader[?, C]] {
    override def contramap[A, B](fa: Reader[A, C])(f: B => A): Reader[B, C] =
      fa match {
        case Reader(g) => Reader(f andThen g)
      }
  }

}