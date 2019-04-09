package com.company.library

import scala.collection.mutable.ListBuffer

class Library(val books: List[Book] = Books.all) {
  var loanedBooks = ListBuffer[Book]()

  def searchIsbn(search: String): Option[Book] = {
    books.find(book => book.ISBN == search)
  }

  def searchAuthor(search: String): List[Book] = {
    books.filter(book => book.author.contains(search))
  }

  def searchTitle(search: String): List[Book] = {
    books.filter(book => book.title.contains(search))
  }

  def lend(book: Book) {
    if (!books.contains(book))
      throw new Exception("Book does not exist")
    else if (book.reference)
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
