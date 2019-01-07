package dogs.data.instances

import dogs.{Applicative, Monoid}
import dogs.data.{Id, Writer}

trait ApplicativeInstances {

  implicit val idApplicative : Applicative[Id] = new Applicative[Id] {
    override def pure[A](a: A): Id[A] = a

    override def ap[A, B](ff: Id[A => B])(f: Id[A]): Id[B] = ff(f)
  }

  implicit def writerApplicative[L: Monoid] : Applicative[Writer[L, ?]] = new Applicative[Writer[L,?]] {
    override def pure[A](a: A): Writer[L, A] = Writer(a)

    override def ap[A, B](ff: Writer[L, A => B])(f: Writer[L, A]): Writer[L, B] =
      ff match {
        case Writer((l1, f1)) =>
          f match {
            case Writer((l2, a)) =>
              Writer((Monoid[L].combine(l1,l2), f1(a)))
          }
      }
  }
}

object ApplicativeInstances extends ApplicativeInstances
