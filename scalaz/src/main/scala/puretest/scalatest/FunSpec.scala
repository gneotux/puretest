package puretest
package scalatestImpl

trait FunSpec[P[_],E] extends org.scalatest.FunSpec
  with org.scalatest.Matchers
  with puretest.FunSpec[P] {

  implicit val Tester: Tester[P,PuretestError[E]]

  def Describe(subject: String)(test: => Unit): Unit =
    describe(subject)(test)

  import ProgramMatchers.Syntax._

  def It[A](condition: String)(program: => P[A]): Unit =
    it(condition){
      program should runWithoutErrors
    }

  def Holds(condition: String)(program: => P[Boolean]): Unit =
    it(condition){
      program should beSatisfied
    }
}

