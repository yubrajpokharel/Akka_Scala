import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yubrajpokharel on 12/10/17.
  */
class HelloActor extends Actor{
  override def receive = {
    case "hello" => println("hello message is received")
    case _ => println("Opps I think I missed it..")
  }
}

object main
  //extends App
{

  //creating a actor system
  val system = ActorSystem("helloworld")

  //creating and starting an actor system
  val helloActor = system.actorOf(Props[HelloActor], name="helloActor")

  //sending message to an actor
  helloActor ! "hello"
  helloActor ! "namaste" //namaste is a nepali/hindi word for the greeting like hello/hi ;)

  //shutting down the actor system
  system.shutdown
}
