package dev.sharek.examples.bookstore

import dev.sharek.examples.models.Book
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import io.circe.generic.auto._

class BookStoreEndpoint(val basePath: String) {

  val bookBody = jsonBody[Book]
    .description("The book")
    .example(Book("Pride and Prejudice"))

  val getBook: Endpoint[Unit, Unit, Unit, Book, Any] =
    endpoint
      .description("Returns a Book, if the user is authorized")
      .get
      .in(basePath / "books")
      .out(bookBody)

}
