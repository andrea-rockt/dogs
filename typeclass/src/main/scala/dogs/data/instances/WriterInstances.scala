package dogs.data.instances

import dogs.data.Writer
import dogs._



trait WriterInstances {

  implicit def writerEq[L: Eq, A: Eq]: Eq[Writer[L, A]] = (x: Writer[L, A], y: Writer[L, A]) =>
    x.run match {
      case (l1, a1) =>
        y.run match {
          case (l2, a2) =>
            l1 == l2 && a1 == a2
        }
    }

  implicit def writerMonad[L: Monoid] : Monad[Writer[L, ?]] = new Monad[Writer[L, ?]] {

    override def pure[A](a: A): Writer[L, A] = Writer(a)

    override def ap[A, B](ff: Writer[L, A => B])(f: Writer[L, A]): Writer[L, B] =
      ff match {
        case Writer((l1, f1)) =>
          f match {
            case Writer((l2, a)) =>
              Writer((Monoid[L].combine(l1,l2), f1(a)))
          }
      }

    override def flatMap[A, B](fa: Writer[L, A])(f: A => Writer[L, B]): Writer[L, B] =
      fa match {
        case Writer((l1, a1)) =>
          f(a1) match {
            case Writer((l2, a2)) =>
              Writer((Semigroup[L].combine(l1,l2), a2))
          }
      }
  }

}