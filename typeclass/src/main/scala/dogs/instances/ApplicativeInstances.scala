package dogs.instances

import dogs.Applicative

trait ApplicativeInstances {

  implicit val listApplicative : Applicative[List] = new Applicative[List] {

    override def pure[A](a: A): List[A] = List(a)

    override def ap[A, B](ff: List[A => B])(f: List[A]): List[B] =
      for {
        a <- f
        fa <- ff
      } yield fa(a) //we kinda cheated here
  }


  implicit val optionApplicative : Applicative[Option] = new Applicative[Option] {
    override def pure[A](a: A): Option[A] = Some(a)

    override def ap[A, B](ff: Option[A => B])(f: Option[A]): Option[B] =
      ff match  {
        case Some(g) => f match {
          case Some(a) => Some(g(a))
          case None => None
        }
        case None => None
      }
  }
}

object ApplicativeInstances extends ApplicativeInstances