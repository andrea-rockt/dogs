package dogs.instances

import dogs.laws.discipline.FunctorTests

class FunctorSpec extends DogsSuite {
  checkAll("List functor", FunctorTests[List].functor[String, String, String])
  checkAll("Option functor", FunctorTests[Option].functor[String, String, String])
}
