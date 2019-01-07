package dogs.laws.discipline

import dogs.laws.FunctorLaws
import dogs.{Eq, Functor}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Cogen}
import org.typelevel.discipline.Laws

trait FunctorTests[F[_]] extends Laws {

  def laws: FunctorLaws[F]

  def functor[A: Arbitrary, B:Arbitrary, C:Arbitrary](implicit
                                                      ArbFA: Arbitrary[F[A]],
                                                      CogenA: Cogen[A],
                                                      CogenB: Cogen[B],
                                                      CogenC: Cogen[C],
                                                      EqFA: Eq[F[A]],
                                                      EqFC: Eq[F[C]]): RuleSet =
    new DefaultRuleSet(
      "functor",
      None,
      "identity" -> forAll(laws.functorIdentity[A] _),
      "composition" -> forAll(laws.functorComposition[A,B,C] _)

    )

}

object FunctorTests {
  def apply[F[_]: Functor]: FunctorTests[F] = new FunctorTests[F] {
    override def laws: FunctorLaws[F] = FunctorLaws[F]
  }
}


