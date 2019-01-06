package dogs.laws.discipline

import dogs.{Eq, Semigroup}
import dogs.laws.SemigroupLaws
import org.scalacheck.Arbitrary
import org.typelevel.discipline.Laws
import org.scalacheck.Prop.forAll

trait SemigroupTests[A] extends Laws {

  def laws: SemigroupLaws[A]

  def semigroup(implicit arbA: Arbitrary[A], eq: Eq[A]): RuleSet =
    new DefaultRuleSet(
      "semigroup",
      None,
      "associative" -> forAll(laws.semigroupAssociative _)
    )

}

object SemigroupTests {
  def apply[A: Semigroup]: SemigroupTests[A] = new SemigroupTests[A] {
    override def laws: SemigroupLaws[A] = SemigroupLaws[A]
  }
}
