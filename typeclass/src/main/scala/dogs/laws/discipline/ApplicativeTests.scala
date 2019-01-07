package dogs.laws.discipline

import dogs.laws.ApplicativeLaws
import dogs.{Applicative, Eq}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Cogen}

trait ApplicativeTests[F[_]] extends FunctorTests[F] {

  def laws: ApplicativeLaws[F]

  def applicative[A: Arbitrary, B:Arbitrary, C:Arbitrary](implicit ArbFA: Arbitrary[F[A]],
                                                                   ArbFAB: Arbitrary[F[A=>B]],
                                                                   ArbFBC: Arbitrary[F[B=>C]],
                                                                   CogenA: Cogen[A],
                                                                   CogenB: Cogen[B],
                                                                   CogenC: Cogen[C],
                                                                   EqFA: Eq[F[A]],
                                                                   EqFB: Eq[F[B]],
                                                                   EqFC: Eq[F[C]]): RuleSet =
  new DefaultRuleSet(
      "applicative",
      Some(functor[A,B,C]),
      "identity" -> forAll(laws.applicativeIdentity[A] _),
      "homomorphism" -> forAll(laws.applicativeHomomorphism[A,B] _),
      "interchange" -> forAll(laws.applicativeInterchange[A,B] _ ),
      "composition" -> forAll(laws.applicativeComposition[A,B,C] _)
    )

}

object ApplicativeTests {
  def apply[F[_]: Applicative]: ApplicativeTests[F] = new ApplicativeTests[F] {
    override def laws: ApplicativeLaws[F] = ApplicativeLaws[F]
  }
}


