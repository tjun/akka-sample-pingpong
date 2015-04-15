import akka.actor.{Props, ActorSystem, Actor}

class Pinger extends Actor {
  def receive = {
    case "Pong" =>
      println("Ping!")
      sender ! "Ping"
  }
}

class Ponger extends Actor {
  def receive = {
    case "Ping" =>
      println("Pong!")
      sender ! "Pong"
  }
}

object PingPong extends App {
  val system = ActorSystem()
  val pinger = system.actorOf(Props[Pinger])
  val ponger = system.actorOf(Props[Ponger])

  pinger.tell("Pong", ponger)

  Thread.sleep(1000)
  system.shutdown
}