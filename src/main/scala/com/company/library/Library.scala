package com.company.library

class Library (val books: List[Book] = Books.all) {

  def searchIsbn(isbn: String): Book = {
    var result: Book = null
    for (book <- books if book.ISBN == isbn) { result = book }

    result
  }
}
