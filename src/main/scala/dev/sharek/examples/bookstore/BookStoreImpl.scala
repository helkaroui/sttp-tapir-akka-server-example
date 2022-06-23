package dev.sharek.examples.bookstore

import dev.sharek.examples.models.Book
import sttp.tapir.server.ServerEndpoint.Full

import scala.concurrent.{ExecutionContext, Future}

case class BookStoreImpl()(implicit ec: ExecutionContext) {

  def getBook(): Future[Either[Unit, Book]] =
    Future.successful(Right[Unit, Book](Book("Hello !")))

}


object BookStoreImpl {

  def getRoute(base: String)(implicit ec: ExecutionContext): Full[Unit, Unit, Unit, Unit, Book, Any, Future] =
    new BookStoreEndpoint(base).getBook.serverLogic(_ => BookStoreImpl().getBook())
}