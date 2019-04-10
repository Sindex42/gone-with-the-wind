package com.company.library

import java.time.LocalDate

case class Loan(book: Book, name: String, date: LocalDate = LocalDate.now)
