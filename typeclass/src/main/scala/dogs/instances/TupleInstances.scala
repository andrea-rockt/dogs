package dogs.instances

import dogs.Eq

trait TupleInstances {

  implicit def eq2[A: Eq, B: Eq]: Eq[(A, B)] =
    (x: (A, B), y: (A, B)) =>
      Eq[A].eqv(x._1, y._1) &&
      Eq[B].eqv(x._2, y._2)

  implicit def eq3[A: Eq, B: Eq, C: Eq]: Eq[(A, B, C)] =
    (x: (A, B, C), y: (A, B, C)) =>
      Eq[A].eqv(x._1, y._1) &&
      Eq[B].eqv(x._2, y._2) &&
      Eq[C].eqv(x._3, y._3)

  implicit def eq4[A: Eq, B: Eq, C: Eq, D: Eq]: Eq[(A, B, C, D)] =
    (x: (A, B, C, D), y: (A, B, C, D)) =>
      Eq[A].eqv(x._1, y._1) &&
        Eq[B].eqv(x._2, y._2) &&
        Eq[C].eqv(x._3, y._3) &&
        Eq[D].eqv(x._4, y._4)

}
