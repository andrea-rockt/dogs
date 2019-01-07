package dogs.data.instances

import dogs.data.{Id, Writer}
import dogs.laws.discipline.ApplicativeTests
import org.scalacheck.Arbitrary

class ApplicativeSpec extends DogsDataSuite {

  {
    implicit def arbitraryWriter[A:Arbitrary] : Arbitrary[Writer[String, A]]  =
      Arbitrary(Arbitrary.arbitrary[A].map(x => Writer(x)))

    checkAll("Writer Applicative", ApplicativeTests[Writer[String, ?]].functor[String, String, String])
  }

  checkAll("Id Applicative", ApplicativeTests[Id].applicative[String,String,String])

}
