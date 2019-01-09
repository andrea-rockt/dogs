package dogs.data.instances

import dogs.data.{Id, Writer}
import dogs.laws.discipline.FunctorTests
import org.scalacheck.Arbitrary

class FunctorSpec extends DogsDataSuite {

  {
    implicit def arbitraryWriter[A:Arbitrary] : Arbitrary[Writer[Vector[String], A]]  =
      Arbitrary(Arbitrary.arbitrary[A].map(x => Writer(x)))

    checkAll("Writer functor", FunctorTests[Writer[Vector[String], ?]].functor[String, String, String])
  }

  checkAll("Id functor", FunctorTests[Id].functor[String, String, String])


}
