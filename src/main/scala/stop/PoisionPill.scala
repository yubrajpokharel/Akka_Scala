package stop

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

/**
  * Created by yubrajpokharel on 12/13/17.
  */

case object Message2

class PoisionPill extends Actor {
  override def receive = {
    case Message2 => println("get an call from director")
    case _ => println("Opps wrong number")
  }

  override def postStop() = {
    println("actor is stopped by poision pill")
  }

}

object runner
  //extends App
{
  val system  = ActorSystem("poisionPill")
  val actor = system.actorOf(Props[PoisionPill], name = "poisionPilledActor")
  actor ! "hi how are you"
  actor ! Message2
  actor ! PoisonPill
  system.stop(actor)
  system.terminate()
}
