package com.company.library

import scala.collection.mutable.ListBuffer

class Library(val books: Books.type = Books) {
  var loanedBooks = ListBuffer[Book]()

  def searchIsbn(isbn: String): Book = {
    var result: Book = null
    for (book <- books.all if book.ISBN == isbn) { result = book }

    result
  }

  def searchAuthor(search: String): Set[Book] = {
    var result = Set[Book]()
    for (book <- books.all if book.author.contains(search)) { result += book }

    result
  }

  def searchTitle(search: String): Set[Book] = {
    var result = Set[Book]()
    for (book <- books.all if book.title.contains(search)) { result += book }

    result
  }

  def lend(book: Book) {
    loanedBooks += book
  }

  def isOnLoan(book: Book): Boolean = {
    loanedBooks.contains(book)
  }
}
