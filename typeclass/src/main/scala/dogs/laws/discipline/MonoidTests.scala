package dogs.laws.discipline

import dogs.laws.MonoidLaws
import dogs.{Eq, Monoid}
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

trait MonoidTests[A] extends SemigroupTests[A] {

  def laws: MonoidLaws[A]

  def monoid(implicit arbA: Arbitrary[A], eq: Eq[A]): RuleSet =
    new DefaultRuleSet(
      "monoid",
      Some(semigroup),
      "left identity" -> forAll(laws.monoidLeftIdentity _),
      "right identity" -> forAll(laws.monoidRightIdentity _)

  )

}

object MonoidTests {
  def apply[A: Monoid]: MonoidTests[A] = new MonoidTests[A] {
    override def laws: MonoidLaws[A] = MonoidLaws[A]
  }
}


