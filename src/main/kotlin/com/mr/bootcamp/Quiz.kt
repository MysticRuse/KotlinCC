package com.mr.bootcamp

import java.util.Random


const val MAX_BORROWS = 10
class Book(val title: String, val author: String, val year: Int, var pages: Int) {

//    object general.Constants {
//        const val BASE_URL = "http://lindenbooks.com"
//    }

    companion object {
        val BASE_URL = "https://www.lindenbooks.com/"
    }

    fun getBookPair() : Pair<String, String> {
        return Pair(title, author)
    }

    fun getBookTriple() : Triple<String, String, Int> {
        return Triple(title, author, year)
    }

    fun canBorrow(hasBooks: Int) : Boolean {
        return hasBooks < MAX_BORROWS
    }

    fun printUrl() {
        println("$BASE_URL$title.html")
    }
}
fun Book.weight() : Double { return (this.pages * 1.5) }

fun Book.tornPages(numTorn: Int) = if (this.pages > numTorn)  pages -= numTorn else this.pages = 0

class Puppy() {
    fun playWithBook(book: Book) {
        val randNum = Random().nextInt(12)
        book.tornPages(randNum)
    }
}

fun main(args: Array<String>) {

    var fortune: String
//    for (i in 1..10) {
//        fortune = getFortune(getBirthday())
//        println("Your fortune is: $fortune")
//        if (fortune.contains("Take it easy")) break
//    }

    println("---- Get fortune cookie ---")
    println("Your fortune is: ${ getFortuneCookie() }")
    println()

    println("---- Get book information ---")
    val book = Book("Take it easy",  "Brendan Shannon", 1980, 300)
    println("Here is your book ${ book.getBookPair().first } written by ${book.getBookPair().second} in ${book.getBookTriple().third}")

    println("---- Puppy playing ------")
    val pup = Puppy()
    val pupBook = Book("Oliver Twist", "Charles Dickens", 1837, 200)
    while (pupBook.pages > 0) {
        pup.playWithBook(pupBook)
        println("${ pupBook.pages } left in ${ pupBook.title }")
    }
    println("Sad puppy, no more pages in ${book.title}. ")
}

fun getBirthday(): Int {
    print("Enter your birthday: ")
    return readlnOrNull()?.toIntOrNull() ?: 1
}

fun getFortune(birthday: Int): String {
    val fortunes = listOf("You will have a great day!",
        "Things will go well for you today!",
        "Enjoy a wonderful day of success.",
        "Be humble and all will turn out well.",
        "Today is a good day for exercising restraint.",
        "Take it easy and enjoy life!",
        "Treasure your friends because they are your greatest fortune.",)

    val index = birthday%fortunes.size
    return fortunes[index]
}

fun getFortuneCookie(): String {
    val fortunes = listOf("You will have a great day!",
        "Things will go well for you today!",
        "Enjoy a wonderful day of success.",
        "Be humble and all will turn out well.",
        "Today is a good day for exercising restraint.",
        "Take it easy and enjoy life!",
        "Treasure your friends because they are your greatest fortune.",)

    print("Enter your birthday: ")
    val birthday: String? = readlnOrNull()
    val birthdayNumber = birthday?.toIntOrNull()
    if (birthdayNumber != null) {
        val index = birthdayNumber%fortunes.size
        return fortunes[index]
    }
    return ""
}
