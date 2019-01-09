package dogs.instances

import dogs.{Eq, Monad, Monoid}

trait ListInstances {

  implicit def listEq[A]: Eq[List[A]] = Eq.fromUniversalEquality[List[A]]

  implicit val listMonad: Monad[List] = new Monad[List] {
    override def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)

    override def pure[A](a: A): List[A] = List(a)

    override def ap[A, B](fab: List[A => B])(fa: List[A]): List[B] =
      for { //cheating while showing an equivalence, also known as scala> fab.flatMap(f => fa.map(a => f(a)))
        f <- fab
        a <- fa
      } yield f(a)
  }

  implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    override def empty: List[A] = List.empty[A]

    override def combine(x: List[A], y: List[A]): List[A] = x ::: y
  }
}
