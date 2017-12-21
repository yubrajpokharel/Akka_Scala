package concurrency


import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Random, Success, Failure}

/**
  * Created by yubrajpokharel on 12/19/17.
  */
object Future2 extends App{

  val f = Future {
    Thread.sleep(Random.nextInt(500))
    1+1
  }


  println("before on complete")
  f.onComplete{
    case Success(value) => println(s"got the message from the future $value")
    case Failure(e) => println(s"got the error name $e")
  }

  println("a............"); Thread.sleep(100)
  println("b............"); Thread.sleep(100)
  println("c............"); Thread.sleep(100)
  println("d............"); Thread.sleep(100)

}
