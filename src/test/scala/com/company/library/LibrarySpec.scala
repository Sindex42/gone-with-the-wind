package com.company.library

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class LibrarySpec extends FunSuite {
  test("Search by ISBN match") {
    val testBooks = List[Book](
      Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"),
      Book("Angels and Demons", "Brown, Dan", "mayiwrko")
    )

    val library = new Library(testBooks)
    library.searchIsbn("pidtkl") shouldBe Option(Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"))
  }

  test("Search by partial author") {
    val testBooks = List[Book](
      Book("Girl with the Dragon Tattoo,The:Millennium Trilogy", "Larsson, Stieg", "hlgbmw"),
      Book("Girl Who Kicked the Hornets' Nest,The:Millennium Trilogy", "Larsson, Stieg", "dtlggzujc"),
      Book("Atonement", "McEwan, Ian", "tfhmtlx")
    )
    val result = List[Book](
      Book("Girl with the Dragon Tattoo,The:Millennium Trilogy", "Larsson, Stieg", "hlgbmw"),
      Book("Girl Who Kicked the Hornets' Nest,The:Millennium Trilogy", "Larsson, Stieg", "dtlggzujc")
    )

    val library = new Library(testBooks)
    library.searchAuthor("Stieg") shouldBe result
  }

  test("Search by partial title") {
    val testBooks = List[Book](
      Book("Life of Pi", "Martel, Yann", "nggzbsum"),
      Book("You are What You Eat:The Plan That Will Change Your Life", "McKeith, Gillian", "xskevg"),
      Book("Harry Potter and the Deathly Hallows", "Rowling, J.K.", "ipszbehyh")
    )
    val result = List(
      Book("Life of Pi", "Martel, Yann", "nggzbsum"),
      Book("You are What You Eat:The Plan That Will Change Your Life", "McKeith, Gillian", "xskevg")
    )

    val library = new Library(testBooks)
    library.searchTitle("Life") shouldBe result
  }

  test("Lending books") {
    val shadowBook = Book("Shadow of the Wind,The", "Zafon, Carlos Ruiz", "uktaqi")
    val library = new Library(List[Book](shadowBook))

    library.lend(shadowBook)
    library.loanedBooks should contain (shadowBook)
  }

  test("Lending a reference book throws an exception") {
    val scalaBook = Book("Scala Cookbook: Recipes for Object-Oriented and Functional Programming", "Alvin Alexander", "qyhawcfrxt", true)
    val library = new Library(List[Book](scalaBook))

    the [Exception] thrownBy {
      library.lend(scalaBook)
    } should have message "Cannot lend reference books"
  }

  test("Lending a book that does not exist throws an exception") {
    val rubyBook = Book("Fake Book", "No Author", "xxxxxxxx")
    val library = new Library()

    the [Exception] thrownBy {
      library.lend(rubyBook)
    } should have message "Book does not exist"
  }

  test("Checking loaned status of a book") {
    val timeBook = Book("Time Traveler's Wife,The", "Niffenegger, Audrey", "zmxmdotjj")
    val shadowBook = Book("Shadow of the Wind,The", "Zafon, Carlos Ruiz", "uktaqi")
    val testBooks = List[Book](timeBook, shadowBook)

    val library = new Library(testBooks)
    library.lend(shadowBook)

    library.isAvailable(shadowBook) shouldBe false
    library.isAvailable(timeBook) shouldBe true
  }

  test("Returning a book") {
    val birdBook = Book("Birdsong", "Faulks, Sebastian", "jioanxkn")
    val testBooks = List[Book](birdBook)

    val library = new Library(testBooks)
    library.lend(birdBook)
    library.isAvailable(birdBook) shouldBe false

    library.returnBook(birdBook)
    library.isAvailable(birdBook) shouldBe true
  }

  test("Returning a book that was not on loan throws an exception") {
    val myBook = Book("My Book", "Me", "xxxxxxxx")
    val library = new Library()

    the [Exception] thrownBy {
      library.returnBook(myBook)
    } should have message "Book was not on loan"
  }
}
