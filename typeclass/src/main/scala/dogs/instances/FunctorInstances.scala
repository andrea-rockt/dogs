package dogs.instances

import dogs.Functor

trait FunctorInstances {

  implicit val listFunctor: Functor[List] = new Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
  }

  implicit val vectorFunctor: Functor[Vector] = new Functor[Vector] {
    override def map[A, B](fa: Vector[A])(f: A => B): Vector[B] = fa.map(f)
  }
}
