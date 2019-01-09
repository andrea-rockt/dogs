package dogs.laws.discipline

import dogs.laws.MonadLaws
import dogs.{Monad, Eq}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Cogen}

trait MonadTests[F[_]] extends ApplicativeTests[F] {

  def laws: MonadLaws[F]

  def monad[A: Arbitrary, B:Arbitrary, C:Arbitrary](implicit ArbFA: Arbitrary[F[A]],
                                                    ArbFB: Arbitrary[F[B]],
                                                    ArbFC: Arbitrary[F[C]],
                                                          ArbFAB: Arbitrary[F[A=>B]],
                                                          ArbFBC: Arbitrary[F[B=>C]],
                                                          CogenA: Cogen[A],
                                                          CogenB: Cogen[B],
                                                          CogenC: Cogen[C],
                                                          EqFA: Eq[F[A]],
                                                          EqFB: Eq[F[B]],
                                                          EqFC: Eq[F[C]]): RuleSet =
    new DefaultRuleSet(
      "monad",
      Some(applicative[A,B,C]),
      "left identity" -> forAll(laws.monadLeftIdentity[A,B] _),
      "right identity" -> forAll(laws.monadRightIdentity[A] _),
      "associativity" -> forAll(laws.monadAssociativity[A,B,C] _ )
    )

}

object MonadTests {
  def apply[F[_]: Monad]: MonadTests[F] = new MonadTests[F] {
    override def laws: MonadLaws[F] = MonadLaws[F]
  }
}


