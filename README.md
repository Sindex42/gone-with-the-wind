# Gone With The Wind


### Getting started

* clone the project
* import via IntelliJ IDEA

### Approach



### Testing
![Imgur](http://i.imgur.com/ZpW7Ygi.png)

### Usage

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

* Creating a date exceeding library LoanLength

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
