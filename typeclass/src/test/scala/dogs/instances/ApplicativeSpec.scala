package dogs.instances

import dogs.laws.discipline.ApplicativeTests

class ApplicativeSpec extends DogsSuite {
  checkAll("List", ApplicativeTests[List].applicative[String, String, String])
  checkAll("Option", ApplicativeTests[Option].applicative[String, String, String])

}
