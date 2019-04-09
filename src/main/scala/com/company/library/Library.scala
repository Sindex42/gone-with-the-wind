package com.company.library

import scala.collection.mutable.ListBuffer

class Library(val books: Books.type = Books) {
  var loanedBooks = ListBuffer[Book]()

  def searchIsbn(search: String): List[Book] = {
    books.all.filter(book => book.ISBN == search)
  }

  def searchAuthor(search: String): List[Book] = {
    books.all.filter(book => book.author.contains(search))
  }

  def searchTitle(search: String): List[Book] = {
    books.all.filter(book => book.title.contains(search))
  }

  def lend(book: Book) {
    if (books.reference.contains(book)) {
      throw new Exception("Cannot lend reference books")
    }
    else {
      loanedBooks += book
    }
  }

  def isOnLoan(book: Book): Boolean = {
    loanedBooks.contains(book)
  }
}
