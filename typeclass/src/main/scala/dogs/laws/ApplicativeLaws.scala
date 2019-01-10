package dogs.laws

import dogs.{Applicative, Apply}

trait ApplicativeLaws[F[_]] extends FunctorLaws[F] with Applicative.ToApplicativeOps with Apply.ToApplyOps {
  implicit def F: Applicative[F]

  def applicativeIdentity[A](a: F[A]): IsEq[F[A]] =
    F.pure(identity[A] _) <*> a <-> a

  def applicativeHomomorphism[A, B](f: A => B, a: A): IsEq[F[B]] =
    F.pure(f) <*> F.pure(a) <-> F.pure(f(a))


  def applicativeInterchange[A, B](ff : F[A => B], a : A) : IsEq[F[B]] = {
    def $(a : A) = (f: A => B) => f(a)
    (ff <*> F.pure(a)) <-> (F.pure($(a)) <*> ff)

  }

  def applicativeComposition[A, B, C](fa : F[A], fab : F[A => B], fbc: F[B => C] ) : IsEq[F[C]] = {
    val compose: (B => C) => (A => B) => (A => C) = _.compose
    (((F.pure(compose) <*> fbc) <*> fab) <*> fa) <-> (fbc <*> (fab <*> fa))
  }

}

object ApplicativeLaws {
  def apply[F[_]](implicit ev: Applicative[F]): ApplicativeLaws[F] =
    new ApplicativeLaws[F] {
      def F: Applicative[F] = ev
    }
}
