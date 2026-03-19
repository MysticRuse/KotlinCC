package general

// Set at compile time. Works only at top level.
// Can define all consts in a separate class
const val rocks =  3

// Value set during program execution.
val number = 5

const val num = 5
fun complexFunctionCall() {}

val result = complexFunctionCall()

const val CONSTANT =  "top-level constant"

object Constants {
    const val CONSTANT2 = "object constant"
}

// To define a const inside a class, wrap it in a companion class
class MyClass {
    companion object {
        const val CONSTANT3 = "constant inside companion"
    }
}


fun main(args: Array<String>) {
    val allBooks = setOf("Macbeth", "Romeo and Juliet", "Hamlet")
    val library = mapOf("Shakespeare" to allBooks)
    val isHamlet = library.any { it.value.contains("Hamlet") }
    println("Is any Hamlet: $isHamlet")

    val moreBooks = mutableMapOf<String, String>("Wilhelm Tell" to "Schiller")
    moreBooks.getOrPut("War and Peace") {"Leo Tolstoy"}
    moreBooks.getOrPut("Hamlet") {"Shakespeare"}
    println("moreBooks: $moreBooks")

    val list1 = mutableListOf("Shakespeare", "Leo Tolstoy") // val cannot be reassigned.
    list1.add("Dostoevesky")

    var list2 = mutableListOf("Shakespeare", "Leo Tolstoy")
    list2.add("Shakespeare")
    list2 = mutableListOf("Shakespeare", "Leo Tolstoy")

    val old = listOf(1, 2, 3)
    val new = old + 4 // immutability with functional updates - Compose achieve predictable state - no hidden side-effects, no surprise mutations.
    println("old: $old, new: $new")

}