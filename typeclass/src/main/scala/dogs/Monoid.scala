package dogs

import simulacrum.typeclass

@typeclass trait Monoid[A] extends Semigroup[A] {
  def empty: A
}
