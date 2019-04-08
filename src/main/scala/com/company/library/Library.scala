package com.company.library

import scala.collection.mutable.ListBuffer

class Library (
  var allBooks: ListBuffer[Book] = Books.all.to[ListBuffer]
) {
  def searchIsbn(isbn: String): Set[Book] = {
    var result = Set[Book]()
    for (book <- allBooks if book.ISBN == isbn) { result += book }

    result
  }

  def searchAuthor(search: String): Set[Book] = {
    var result = Set[Book]()
    for (book <- allBooks if book.author.contains(search)) { result += book }

    result
  }

  def searchTitle(search: String): Set[Book] = {
    var result = Set[Book]()
    for (book <- allBooks if book.title.contains(search)) { result += book }

    result
  }

  def lend(book: Book) {
    allBooks -= book
  }
}
