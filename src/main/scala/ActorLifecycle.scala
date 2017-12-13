import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yubrajpokharel on 12/11/17.
  */

case object ForceRestart

class ActorLifecycle extends Actor {

  println("Inside actor constructor")

  override def preStart() { println("Actor: preStart") }

  override def postStop() { println("Actor: postStop") }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Actor: preRestart")
    println(s" Message: ${message.getOrElse("")}")
    println(s" Reson: ${reason.getCause}")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    println("Actor: postRestart")
    println(s" Reason: ${reason.getMessage}")
    super.postRestart(reason)
  }

  override def receive = {
    case ForceRestart => throw new Exception("Boom")
    case _ => println("Actor receive a message")
  }

}

object lifeCycleDemo
   // extends App
{
  val system = ActorSystem("lifecycle")
  val actorLifecycle = system.actorOf(Props[ActorLifecycle], name = "actorLifeCycle")

  println("sending actor a message")
  actorLifecycle ! ForceRestart
  Thread.sleep(1000)

  println("make actor restart")
  actorLifecycle ! ForceRestart
  Thread.sleep(1000)

  print("stopping actor")
  system.stop(actorLifecycle)

  println("shutting dowm system")
  system.shutdown()
}
