package dogs.examples

import java.time.Instant

import dogs._
import dogs.data.{Writer, WriterOps}

object Main
  extends Applicative.ToApplicativeOps
    with FlatMap.ToFlatMapOps
    with Functor.ToFunctorOps
    with dogs.instances.AllInstances
    with dogs.data.instances.AllInstances {


  type Log = Vector[(LogTag, Instant, String)]
  type Logged[A] = Writer[Log, A]
  val loggedMonad = Monad[Logged]

  import Logged.tell


  def main(args: Array[String]): Unit = {

    def sum(a: Int, b: Int): Logged[Int] = for {
      _ <- tell(Log(SummingPhase(a, b, None), "Summing"))
           c = a + b
      _ <- tell(Log(SummingPhase(a, b, Some(c)), s"Used $a and $b to generate a $c"))
    } yield c

    def multiply(a: Int, b: Int): Logged[Int] = for {
      _ <- tell(Log(MultiplyPhase(a, b, None), "Multiplying"))
           c = a * b
      _ <- tell(Log(MultiplyPhase(a, b, Some(c)), s"Used $a and $b to generate a $c"))
    } yield c

    sum(3, 5).flatMap(multiply(_, 2)).run match {
      case (log, output) =>
        println("Full log")
        println(Log.prettylog(log))
        println("Only tag log")
        println(Log.onlytaglog(log))
        println(s"output is $output")
    }


  }

  sealed trait LogTag

  case class SummingPhase(x: Int, y: Int, result: Option[Int]) extends LogTag

  case class MultiplyPhase(x: Int, y: Int, result: Option[Int]) extends LogTag

  case object SummingPhaseStart extends LogTag

  case object NoTag extends LogTag

  object Log {
    def apply(phase: LogTag, a: String): Log = Vector((phase, Instant.now(), a))

    def apply(a: String): Log = Vector((NoTag, Instant.now(), a))

    def prettylog(log: Log, maybeStartInstant: Option[Instant] = None): String =
      log.map {
        case (NoTag, when, what) => s"[$when] -> $what"
        case (tag, when, what) => s"[$when] $tag\n[$when] -> $what"
      }.mkString("\n")

    def onlytaglog(log: Log, maybeStartInstant: Option[Instant] = None): String =
      log.map {
        case (NoTag, _, _) => s""
        case (tag, when, _) => s"[$when] $tag"
      }.filterNot(_ == "").mkString("\n")
  }

  object Logged extends WriterOps {
    def apply[A: Monoid](a: A): Logged[A] = Writer[Log, A](a)
  }

}
