import akka.actor._

/**
 * Created by yubrajpokharel on 12/11/17.
 */

case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

class Ping(pong: ActorRef) extends Actor{
    var count = 0
    def incrementAndPrint {count+=1; println("ping")}
    override def receive = {
        case StartMessage =>
            incrementAndPrint
            pong ! PingMessage
        case PongMessage =>
            incrementAndPrint
            if(count > 90) {
                sender ! StopMessage
                println("ping stopped")
                context.stop(self)
            }else{
                sender ! PingMessage
            }
        case _ => println("ping got something unexpected")
    }
}

class Pong extends Actor{
    override def receive = {
        case PingMessage =>
            println(" pong")
            sender ! PongMessage
        case StopMessage =>
            println("pong stopped")
            context.stop(self)
        case _ => println("pong got something unexpected")
    }
}

object PingPong extends App{
    val system = ActorSystem("PingPongSystem")
    val pong = system.actorOf(Props[Pong], name = "ping")
    val ping = system.actorOf(Props(new Ping(pong)), name = "pong")
    ping ! StartMessage

    system.shutdown
}
