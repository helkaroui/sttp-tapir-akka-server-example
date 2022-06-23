package dev.sharek.examples

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import dev.sharek.examples.bookstore.{BookStoreEndpoint, BookStoreImpl}
import sttp.capabilities.WebSockets
import sttp.capabilities.akka.AkkaStreams
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.swagger.SwaggerUIOptions
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem = ActorSystem("system")

    val swaggerEndpoints: List[ServerEndpoint[AkkaStreams with WebSockets, Future]] =
      SwaggerInterpreter(
        swaggerUIOptions = SwaggerUIOptions(List("api", "swagger"), "swagger.yaml", Nil, useRelativePaths = true)
      ).fromEndpoints(List(
        new BookStoreEndpoint("api").getBook
      ), "My App", "1.0")

    val serverEndpoints = List(
      BookStoreImpl.getRoute("api")
    )

    val routes: Route = AkkaHttpServerInterpreter().toRoute(serverEndpoints ++ swaggerEndpoints)

    Http()
      .newServerAt("localhost", 8000)
      .bind(routes)
  }
}
