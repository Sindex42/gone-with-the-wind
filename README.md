# Gone With The Wind
<a href="https://codeclimate.com/github/Sindex42/gone-with-the-wind/maintainability"><img src="https://api.codeclimate.com/v1/badges/f72306b513234e01ecdf/maintainability" /></a>

A library program to manage searches, lending and returning of books. All required and optional user stories completed apart from the last one regarding fines for users who are late returning their books.   

## Getting started

* clone the project
* import via IntelliJ IDEA

## Approach

* I Started with the full ISBN search as that only required complete string matching and would only be return a single book.
* For the partial author and title search, I initially grouped the search results into a set, as they cannot have duplicate elements and are unordered. I later reverted to returning the search results as a list for simplicity's sake.
* My approach to managing reference books was to add another field to each book that defaults to false. This meant that I didn't have to make any large changes to the way Library imports books.
* I refactored the tests to make them more readable by using a beforeEach block. This reduced the repetition caused by creating and seeding a new library with books.
* My initial approach to lending books was to add a copy of the book to a new listBuffer. I chose listBuffers as the collection would be changed frequently and thus would benefit from being mutable.
I later created a separate Loan case class to keep track of who borrowed a book and when they borrowed it.

#### Assumptions

* ISBNs are unique
* Searches are case sensitive
* Libraries only have one copy of each book

## Testing
![Imgur](http://i.imgur.com/ZpW7Ygi.png)

## Usage

1. Start sbt `sbt`
2. Start the scala console `console`
3. Import the package `import com.company.library._`
4. Create a new Library `val library = new Library`

![Imgur](http://i.imgur.com/dRFMnUQ.png)

##### Searching books by partial title
![Imgur](http://i.imgur.com/JkPqtSV.png)

##### Searching books by partial author
![Imgur](http://i.imgur.com/Cz3J2z2.png)

##### Searching books by full ISBN
![Imgur](http://i.imgur.com/4M96vS5.png)

##### Lending a book
![Imgur](http://i.imgur.com/wV3cTeP.png)

##### Trying to lend a reference book
![Imgur](http://i.imgur.com/SVetv1U.png)

##### Lending and returning a book
* Loans must include a name

* Library.loan creates an object with the book, name, and date of loan

* That book is now not in stock and also on loan

* Returning a book reverses this and removes the loan

![Imgur](http://i.imgur.com/Tn8ZLcd.png)

##### Determining which books are late

* Creating a date exceeding library LoanLength (LoanLength stored in a constant in the Library class)

![Imgur](http://i.imgur.com/oTxK1hX.png)

* Creating a late loan and a new library with one book and that loan

![Imgur](http://i.imgur.com/fYPjgtO.png)

* Lending a book today

* Finding all loans

* Finding late loans

![Imgur](http://i.imgur.com/n8rRtTO.png)

---

## Original Instructions

You have a library of books and are offering them to the world - you are lending so many books now that it is becoming hard to keep track of what you have.  You decided to use your programming fu to build an application which can keep track of them for you.

##### Getting started

* clone the project
* import via IntelliJ IDEA

##### Requirements

* implements the user stories listed below (optional ones not required)
* compiles
* has tests
* frequent commits

##### User Stories

```text
As a visitor,
So that I can find books I am looking for,
I need to be able search books by partial title
```

```text
As a visitor,
So that I can find books I am looking for,
I need to be able search books by partial author
```

```text
As a visitor,
So that I can find books I am looking for,
I need to be able to search by full ISBN
```

```text
As a librarian,
So that I can help my community,
I need to be able to lend books to visitors
```

```text
As a librarian,
So that I can protect my expensive books,
I don't want to lend reference books
```

```text
As a librarian,
So that I can manage my library correctly,
I need to know whether a book is available or on loan
```

---

##### Optional extra stories

```text
As a librarian,
So that I can update my stock levels,
I need to be able update the library when a book is returned
```

```text
As a librarian,
So that I can manage my library correctly,
I need to know who has a book that is on loan
```

```text
As a librarian,
So that I can manage my library correctly,
I need to know which books are late
```

```text
As a librarian,
So that I can manage my library correctly,
I need to know who has a book that is late
```

```text
As a librarian,
So that I can manage my library correctly,
I want to fine users who are late returning their books
```
