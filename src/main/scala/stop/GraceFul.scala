package stop


import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.gracefulStop

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration


/**
  * Created by yubrajpokharel on 12/13/17.
  */
class GraceFul extends Actor{
  def receive = {
    case _ => {
      Thread.sleep(5000)
      println(s"got a message  !!!")
    }
  }

  override def postStop() = println("Actor is Stopped")
}


object testActor
  extends App
{
  val system = ActorSystem("graceful")
  val actor = system.actorOf(Props[GraceFul], name = "graceful")
  actor ! "hi"
  actor ! "hellp"

  try{
    val stopped: Future[Boolean] = gracefulStop(actor, Duration.create(2, TimeUnit.SECONDS))
    Await.result(stopped, Duration.create(3, TimeUnit.SECONDS))
    println("testActor was stopped")
  }catch {
    case e:Exception => println(e.printStackTrace())
  }finally {
    system.terminate()
  }

}