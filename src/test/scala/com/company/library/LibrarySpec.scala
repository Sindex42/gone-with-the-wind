package com.company.library

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import com.company.library

class LibrarySpec extends FunSuite {

  test("Search by ISBN match") {
    val library = new Library
    library.searchIsbn("pidtkl") shouldBe Book("Da Vinci Code,The", "Brown, Dan", "pidtkl")
  }

  test("Search by partial Author (one book)") {
    val library = new Library
    library.searchAuthor("Sebold") shouldBe Set(Book("Lovely Bones,The", "Sebold, Alice", "stniskzb"))
  }

  test("Search by partial Author (multiple books)") {
    val library = new Library
    val result = Set(
      Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"),
      Book("Angels and Demons", "Brown, Dan", "mayiwrko"),
      Book("Lost Symbol,The", "Brown, Dan", "ewvxsoql"),
      Book("Deception Point", "Brown, Dan", "wixnjrdb"),
      Book("Digital Fortress", "Brown, Dan", "vwgikppxt")
    )

    library.searchAuthor("Dan") shouldBe result
  }
}
