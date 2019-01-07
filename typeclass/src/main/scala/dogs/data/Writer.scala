package dogs.data

import dogs.Monoid

case class Writer[L, A](run: (L, A)) {
  def runL: L = run._1
  def runA: A = run._2
}

object Writer {
  def apply[L: Monoid, T](run: => T): Writer[L, T] = Writer((Monoid[L].empty, run))
}
