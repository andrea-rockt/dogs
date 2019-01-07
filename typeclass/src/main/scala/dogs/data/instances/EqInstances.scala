package dogs.data.instances

import dogs.Eq
import dogs.data.Writer

trait EqInstances {
  implicit def writerEq[L: Eq, A: Eq]: Eq[Writer[L, A]] = (x: Writer[L, A], y: Writer[L, A]) =>
    x.run match {
      case (l1, a1) =>
        y.run match {
          case (l2, a2) =>
            l1 == l2 && a1 == a2
        }
    }

  implicit def idEq[A: Eq] : Eq[A] = (x : A, y : A) => Eq[A].eqv(x,y)
}
