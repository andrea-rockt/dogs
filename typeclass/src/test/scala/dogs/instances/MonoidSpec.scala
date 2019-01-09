package dogs.instances

import dogs.laws.discipline.MonoidTests

class MonoidSpec extends DogsSuite {

    checkAll("Int", MonoidTests[Int].monoid)
    checkAll("Double", MonoidTests[Double].monoid)
    checkAll("List[Int]", MonoidTests[List[Int]].monoid)
    checkAll("List[String]", MonoidTests[List[String]].monoid)
}
