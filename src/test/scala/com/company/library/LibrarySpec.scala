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
    library.searchIsbn("pidtkl") shouldBe List(Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"))
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
    val testBooks = List[Book](shadowBook)

    val library = new Library(testBooks)
    library.lend(shadowBook)

    library.loanedBooks should contain (shadowBook)
  }

  test("Cannot lend a reference book") {
    val rubyBook = Book("Practical Object-Oriented Design in Ruby", "Sandi Metz", "qyhawcfrxt")
    val testBooks = List[Book]()
    val referenceBooks = List[Book](rubyBook)

    val library = new Library(testBooks, referenceBooks)

    the [Exception] thrownBy {
      library.lend(rubyBook)
    } should have message "Cannot lend reference books"
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
}
