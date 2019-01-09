package dogs.instances

import dogs.{Eq, Monad, Monoid}

trait VectorInstances {

  implicit def vectorEq[A]: Eq[Vector[A]] = Eq.fromUniversalEquality[Vector[A]]

  implicit val vectorMonad: Monad[Vector] = new Monad[Vector] {

    override def flatMap[A, B](fa: Vector[A])(f: A => Vector[B]): Vector[B] = fa.flatMap(f)

    override def pure[A](a: A): Vector[A] = Vector(a)

    override def ap[A, B](fab: Vector[A => B])(fa: Vector[A]): Vector[B] =
      for { //cheating while showing an equivalence, also known as scala> fab.flatMap(f => fa.map(a => f(a)))
        f <- fab
        a <- fa
      } yield f(a)
  }


  implicit def vectorMonoid[A]: Monoid[Vector[A]] = new Monoid[Vector[A]] {
    override def empty: Vector[A] = Vector.empty[A]

    override def combine(x: Vector[A], y: Vector[A]): Vector[A] = x ++ y
  }
}
