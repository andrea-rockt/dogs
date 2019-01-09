package dogs.data.instances

import dogs.Eq
import dogs.data.{Id, State, Writer}
import dogs.laws.discipline.MonadTests
import org.scalacheck.Arbitrary

class MonadSpec extends DogsDataSuite {

  checkAll("Id", MonadTests[Id].monad[SomeA, SomeB, SomeC])

  {

    implicit def arbitraryWriter[A: Arbitrary]: Arbitrary[Writer[String, A]] =
      Arbitrary(Arbitrary.arbitrary[A].map(x => Writer(x)))

    checkAll("Writer", MonadTests[Writer[Fixed, ?]].monad[SomeA, SomeB, SomeC])

  }

  {

    implicit def arbitraryState[A: Arbitrary]: Arbitrary[State[Fixed, A]] =
      Arbitrary(Arbitrary.arbitrary[A]
                         .map(x => State[Fixed, A](s => (s,x))))

    implicit def stateEq[S: Arbitrary: Eq, A : Eq]: Eq[State[S,A]] = { (x: State[S, A], y: State[S, A]) =>
      Arbitrary.arbitrary[S]
               .map(s0 => Eq[(S,A)].eqv(x.run(s0), y.run(s0)))
               .sample
               .getOrElse(false)
    }




    checkAll("State", MonadTests[State[Fixed, ?]].monad[SomeA, SomeB, SomeC])

  }
}
