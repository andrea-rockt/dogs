package dogs.instances

import dogs.{Eq, Monad}

trait OptionInstances {
  implicit def optionEq[A]: Eq[Option[A]] = Eq.fromUniversalEquality[Option[A]]

  implicit val optionMonad: Monad[Option] = new Monad[Option] {

    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)

    override def pure[A](a: A): Option[A] = Option(a)

    override def ap[A, B](fab: Option[A => B])(fa: Option[A]): Option[B] =
      fab match {
        case Some(f) => fa match {
          case Some(a) => Some(f(a))
          case None => None
        }
        case None => None
      }
  }
}