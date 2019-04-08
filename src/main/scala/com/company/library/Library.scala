package com.company.library

class Library (val books: List[Book] = Books.all) {

  def searchIsbn(isbn: String): Book = {
    var result: Book = null
    for (book <- books if book.ISBN == isbn) { result = book }

    result
  }

  def searchAuthor(search: String): Set[Book] = {
    var result = Set[Book]()
    for (book <- books if book.author.contains(search)) { result += book }

    result
  }

  def searchTitle(search: String): Set[Book] = {
    var result = Set[Book]()
    for (book <- books if book.title.contains(search)) { result += book }

    result
  }
}
