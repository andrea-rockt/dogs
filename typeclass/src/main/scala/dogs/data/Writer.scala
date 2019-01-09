package dogs.data

import dogs.Monoid

case class Writer[L: Monoid, A](run: (L, A)) {
  def runL: L = run._1

  def runA: A = run._2

  def tell(l: L) = Writer(run match {
    case (log, a) =>
      (Monoid[L].combine(log, l), a)
  })
}


trait WriterOps {
  def tell[L: Monoid](l: L): Writer[L, Unit] = Writer((l, ()))
}

object Writer {
  def apply[L: Monoid, T](run: => T): Writer[L, T] = Writer((Monoid[L].empty, run))


}
