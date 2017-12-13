package stop

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yubrajpokharel on 12/13/17.
  */
object stopMessage //extends App
{
  val system = ActorSystem("normalStop")
  val actor = system.actorOf(Props[MyActor], name = "myactor")
  actor ! Message
  system.stop(actor)
  system.terminate()
}

case object Message

class MyActor extends Actor {
  def receive = {
    case Message => println(s"Actor get a call to act")
    case _ => println("Opps wrong number")
  }
}