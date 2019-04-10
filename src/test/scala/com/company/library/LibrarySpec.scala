package com.company.library

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfterEach
import org.scalatest.Matchers._

import scala.collection.mutable.ListBuffer
import java.time.LocalDate

class SearchLibrarySpec extends FunSuite {
  val testBooks = List[Book](
    Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"),
    Book("Girl with the Dragon Tattoo,The:Millennium Trilogy", "Larsson, Stieg", "hlgbmw"),
    Book("Girl Who Kicked the Hornets' Nest,The:Millennium Trilogy", "Larsson, Stieg", "dtlggzujc"),
    Book("Life of Pi", "Martel, Yann", "nggzbsum"),
    Book("You are What You Eat:The Plan That Will Change Your Life", "McKeith, Gillian", "xskevg"),
  )
  val library = new Library(testBooks)


  test("Search by ISBN match") {
    library.searchIsbn("pidtkl") shouldBe Book("Da Vinci Code,The", "Brown, Dan", "pidtkl")
  }

  test("Search by partial author") {
    val result = List[Book](
      Book("Girl with the Dragon Tattoo,The:Millennium Trilogy", "Larsson, Stieg", "hlgbmw"),
      Book("Girl Who Kicked the Hornets' Nest,The:Millennium Trilogy", "Larsson, Stieg", "dtlggzujc")
    )

    library.searchAuthor("Stieg") shouldBe result
  }

  test("Search by partial title") {
    val result = List(
      Book("Life of Pi", "Martel, Yann", "nggzbsum"),
      Book("You are What You Eat:The Plan That Will Change Your Life", "McKeith, Gillian", "xskevg")
    )

    library.searchTitle("Life") shouldBe result
  }
}

class LendLibrarySpec extends FunSuite with BeforeAndAfterEach {
  val testBooks = List[Book](
    Book("Shadow of the Wind,The", "Zafon, Carlos Ruiz", "uktaqi"),
    Book("Scala Cookbook: Recipes for Object-Oriented and Functional Programming", "Alvin Alexander", "qyhawcfrxt", true),
    Book("Time Traveler's Wife,The", "Niffenegger, Audrey", "zmxmdotjj")
  )
  val shadowBook = Book("Shadow of the Wind,The", "Zafon, Carlos Ruiz", "uktaqi")
  val timeBook = Book("Time Traveler's Wife,The", "Niffenegger, Audrey", "zmxmdotjj")
  val otherBook = Book("Other Book", "No Author", "xxxxxxxx")

  var library: Library = null

  override def beforeEach(): Unit = {
    library = new Library(testBooks)
  }

  test("Lending a book adds a loan and removes it from stock") {
    library.lend(shadowBook, "Elizabeth Rary")

    library.loans should contain (Loan(shadowBook, "Elizabeth Rary"))
    library.stock should not contain (shadowBook)
  }

  test("Lending a reference book throws an exception") {
    val scalaBook = Book("Scala Cookbook: Recipes for Object-Oriented and Functional Programming", "Alvin Alexander", "qyhawcfrxt", true)

    the [Exception] thrownBy {
      library.lend(scalaBook, "Elizabeth Rary")
    } should have message "Cannot lend reference books"
  }

  test("Lending a book that is not in stock throws an exception") {
    the [Exception] thrownBy {
      library.lend(otherBook, "Elizabeth Rary")
    } should have message "Book is not in stock"
  }

  test("Checking loaned status of a book") {
    library.lend(shadowBook, "Elizabeth Rary")

    library.isOnLoan(timeBook) shouldBe false
    library.isOnLoan(shadowBook) shouldBe true
  }

  test("Checking stock status of a book") {
    library.lend(shadowBook, "Elizabeth Rary")

    library.isInStock(timeBook) shouldBe true
    library.isInStock(shadowBook) shouldBe false
  }

  test("Checking lender of a book") {
    library.lend(shadowBook, "Elizabeth Rary")

    library.loans.head.name shouldBe "Elizabeth Rary"
  }

  test("Returning a book removes a loan and adds it back to stock") {
    library.lend(shadowBook, "Elizabeth Rary")
    library.returnBook(shadowBook)

    library.loans should not contain (Loan(shadowBook, "Elizabeth Rary"))
    library.stock should contain (shadowBook)
  }

  test("Returning a book that was not on loan throws an exception") {
    the [Exception] thrownBy {
      library.returnBook(otherBook)
    } should have message "Book was not on loan"
  }

  test("Finding late books") {
    val lateLoan = Loan(shadowBook, "Elizabeth Rary", LocalDate.now.minusDays(library.LoanLength + 1))
    val notlateLoan = Loan(timeBook, "Elizabeth Rary", LocalDate.now.minusDays(library.LoanLength))
    library = new Library(testBooks, ListBuffer(lateLoan, notlateLoan))

    library.findLateBooks shouldBe ListBuffer(lateLoan)
  }

  test("Finding late books' owners") {
    val lateLoan = Loan(shadowBook, "Elizabeth Rary", LocalDate.now.minusDays(library.LoanLength + 1))
    library = new Library(testBooks, ListBuffer(lateLoan))

    library.findLateBooks.head.name shouldBe "Elizabeth Rary"
  }
}
