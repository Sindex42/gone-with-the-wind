package com.company.library

import scala.collection.mutable.ListBuffer

class Library(val books: List[Book] = Books.all) {
  var loans = ListBuffer[Loan]()

  def searchIsbn(search: String): Book = {
    books.find(book => book.ISBN == search).head
  }

  def searchAuthor(search: String): List[Book] = {
    books.filter(book => book.author.contains(search))
  }

  def searchTitle(search: String): List[Book] = {
    books.filter(book => book.title.contains(search))
  }

  def lend(book: Book, name: String) {
    if (!books.contains(book)) throw new Exception("Book does not exist")
    if (book.reference) throw new Exception("Cannot lend reference books")

    loans += new Loan(book, name)
  }

  def returnBook(book: Book) {
    if (!isOnLoan(book)) throw new Exception("Book was not on loan")
    loans -= loans.find(loan => loan.book == book).head
  }

  def isOnLoan(book: Book): Boolean = {
    loans.exists(loan => loan.book == book)
  }
}
