package dogs

import simulacrum.{op, typeclass}

@typeclass trait Eq[A] {

  @op("===") def eqv(x: A, y: A): Boolean

  @op("!==") def neqv(x: A, y: A): Boolean = !eqv(x, y)

}

object Eq {

  def allEqual[A]: Eq[A] = (_: A, _: A) => true

  def fromUniversalEquality[A]: Eq[A] = _ == _

}

