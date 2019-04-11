package com.company.library

import scala.collection.mutable.ListBuffer
import java.time.LocalDate

class Library(
  val books: List[Book] = Books.all,
  val loans: ListBuffer[Loan] = ListBuffer(),
) {

  val LoanLength = 21
  val stock = books.to[ListBuffer]

  def searchIsbn(search: String): Book = {
    books.find(book => book.ISBN == search).head
  }

  def searchAuthor(search: String): List[Book] = {
    books.filter(book => book.author.contains(search))
  }

  def searchTitle(search: String): List[Book] = {
    books.filter(book => book.title.contains(search))
  }

  def lend(book: Book, name: String): Unit = {
    if (!isInStock(book)) throw new Exception("Book is not in stock")
    if (book.reference) throw new Exception("Cannot lend reference books")

    stock -= book
    loans += new Loan(book, name)
  }

  def returnBook(book: Book): Unit = {
    if (!isOnLoan(book)) throw new Exception("Book was not on loan")

    loans -= loans.find(loan => loan.book == book).head
    stock += book
  }

  def isOnLoan(book: Book): Boolean = {
    loans.exists(loan => loan.book == book)
  }

  def isInStock(book: Book): Boolean = {
    stock.contains(book)
  }

  def findLateBooks: ListBuffer[Loan] = {
    loans.filter(loan => loan.date.plusDays(LoanLength) isBefore LocalDate.now)
  }
}
