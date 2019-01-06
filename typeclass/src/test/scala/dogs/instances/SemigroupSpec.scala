package dogs.instances

import dogs.laws.discipline.SemigroupTests

class SemigroupSpec extends DogsSuite {
    checkAll("Int", SemigroupTests[Int].semigroup)
    checkAll("Double", SemigroupTests[Double].semigroup)
    checkAll("List[Int]", SemigroupTests[List[Int]].semigroup)
    checkAll("List[String]", SemigroupTests[List[String]].semigroup)
}
