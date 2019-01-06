package dogs.instances

import dogs.Eq

trait EqInstances {
  implicit def listInstance[A]: Eq[List[A]] = Eq.fromUniversalEquality[List[A]]
}

object EqInstances extends EqInstances
