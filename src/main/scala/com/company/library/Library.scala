package com.company.library

import scala.collection.mutable.ListBuffer

class Library(
  val allBooks: List[Book] = Books.all,
  val referenceBooks: List[Book] = Books.reference
) {
  var loanedBooks = ListBuffer[Book]()

  def searchIsbn(search: String): List[Book] = {
    allBooks.filter(book => book.ISBN == search)
  }

  def searchAuthor(search: String): List[Book] = {
    allBooks.filter(book => book.author.contains(search))
  }

  def searchTitle(search: String): List[Book] = {
    allBooks.filter(book => book.title.contains(search))
  }

  def lend(book: Book) {
    if (referenceBooks.contains(book))
      throw new Exception("Cannot lend reference books")
    else
      loanedBooks += book
  }

  def returnBook(book: Book) {
    loanedBooks -= book
  }

  def isAvailable(book: Book): Boolean = {
    !loanedBooks.contains(book)
  }
}
