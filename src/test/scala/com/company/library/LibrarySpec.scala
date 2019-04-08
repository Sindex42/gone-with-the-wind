package com.company.library

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import com.company.library

class LibrarySpec extends FunSuite {
  val library = new Library

  test("Search by ISBN match") {
    library.searchIsbn("pidtkl") shouldBe Set(Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"))
  }

  test("Search by partial author (one book)") {
    library.searchAuthor("Sebold") shouldBe Set(Book("Lovely Bones,The", "Sebold, Alice", "stniskzb"))
  }

  test("Search by partial author (multiple books)") {
    val result = Set(
      Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"),
      Book("Angels and Demons", "Brown, Dan", "mayiwrko"),
      Book("Lost Symbol,The", "Brown, Dan", "ewvxsoql"),
      Book("Deception Point", "Brown, Dan", "wixnjrdb"),
      Book("Digital Fortress", "Brown, Dan", "vwgikppxt")
    )

    library.searchAuthor("Dan") shouldBe result
  }

  test("Search by partial title") {
    val result = Set(
      Book("Harry Potter and the Deathly Hallows", "Rowling, J.K.", "ipszbehyh"),
      Book("Harry Potter and the Philosopher's Stone", "Rowling, J.K.", "lfzowqpsj"),
      Book("Harry Potter and the Order of the Phoenix", "Rowling, J.K.", "ymjklwq"),
      Book("Harry Potter and the Goblet of Fire", "Rowling, J.K.", "krsmrccb"),
      Book("Harry Potter and the Chamber of Secrets", "Rowling, J.K.", "dvceqblua"),
      Book("Harry Potter and the Prisoner of Azkaban", "Rowling, J.K.", "iamvmb"),
      Book("Harry Potter and the Half-blood Prince:Children's Edition", "Rowling, J.K.", "gdjvia"),
      Book("Harry Potter and the Half-blood Prince", "Rowling, J.K.", "ajaoshq")
    )

    library.searchTitle("Potter") shouldBe result
  }

  test("Lending books") {
    val shadow = Book("Shadow of the Wind,The", "Zafon, Carlos Ruiz", "uktaqi")

    library.lend(shadow)
    library.allBooks should not contain shadow
  }
}
