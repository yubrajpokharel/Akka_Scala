package concurrency

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by yubrajpokharel on 12/19/17.
  */
class Future1 {

}


object Future1 extends App{
  implicit val baseTime = System.currentTimeMillis

  val f = Future {
    Thread.sleep(500)
    1+1
  }

  val result = Await.result(f, Duration.create(1, TimeUnit.SECONDS))
  println(result)
  Thread.sleep(500)
}