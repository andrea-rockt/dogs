import sbt._

object Dependencies {

  lazy val testDependencies = List(
    scalatest,
    scalacheck
  )

  lazy val projectDependencies = List(
    simulacrum,
    shapeless,
    discipline
  )

  lazy val all = projectDependencies ::: testDependencies

  /**
    * ScalaTest is the most flexible and most popular testing tool in the Scala ecosystem.
    * With ScalaTest, you can test Scala, Scala.js (JavaScript), and Java code.
    */
  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.5" % Test

  /**
    * Type classes rock. Alas, their encoding in Scala requires a lot of boilerplate, which doesn't rock.
    * There is inconsistency between projects, where type classes are encoded differently.
    * There is inconsistency within projects, where object-oriented forwarders (aka. ops, syntax) accidentally
    * differ in exact parameter lists or forwarders are missing where they are expected to be.
    * Even in disciplined teams, the bike-shedding opportunities alone are a source of lost productivity.
    * This project addresses these concerns by introducing first class support for type classes in Scala
    */
  lazy val simulacrum = "com.github.mpilquist" %% "simulacrum" % "0.12.0"


  /**
    * shapeless is a type class and dependent type based generic programming library for Scala.
    * It had its origins in several talks by Miles Sabin (@milessabin), given over the course of 2011,
    * on implementing scrap your boilerplate and higher rank polymorphism in Scala.
    * Since then it has evolved from being a resolutely experimental project into a library which,
    * while still testing the limits of what's possible in Scala, is being used widely in production systems
    * wherever there are arities to be abstracted over and boilerplate to be scrapped.
    */
  lazy val shapeless = "com.chuusai" %% "shapeless" % "2.3.3"


  /**
    * Originally intended for internal use in spire, this library helps libraries declaring type classes
    * to precisely state the laws which instances need to satisfy, and takes care of not checking derived
    * laws multiple times.
    */
  lazy val discipline = "org.typelevel" %% "discipline" % "0.10.0"

  /**
    * ScalaCheck is a library written in Scala and used for automated property-based testing of Scala or Java programs.
    */
  lazy val scalacheck = "org.scalacheck" %% "scalacheck" % "1.14.0" % Test
}
