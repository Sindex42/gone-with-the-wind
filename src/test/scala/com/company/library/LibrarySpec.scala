package com.company.library

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import com.company.library

class LibrarySpec extends FunSuite {

  test("Search by ISBN match") {
    val library = new Library
    library.searchIsbn("pidtkl") shouldBe Book("Da Vinci Code,The", "Brown, Dan", "pidtkl")
  }

}
