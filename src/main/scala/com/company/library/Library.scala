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
    isBookInLibrary(book)
    isReference(book)
    loanedBooks += book
  }

  def returnBook(book: Book) {
    isOnLoan(book)
    loanedBooks -= book
  }

  def isAvailable(book: Book): Boolean = {
    !loanedBooks.contains(book)
  }


  private def isBookInLibrary(book: Book) {
    if (!books.contains(book)) throw new Exception("Book does not exist")
  }

  private def isReference(book: Book) {
    if (book.reference) throw new Exception("Cannot lend reference books")
  }

  private def isOnLoan(book: Book) {
    if (!loanedBooks.contains(book)) throw new Exception("Book was not on loan")
  }

}
