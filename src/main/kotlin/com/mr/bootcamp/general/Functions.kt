package com.mr.kotlin.bootcamp.general


// Simplifying function declarations
fun generateAnswerString(count: Int, countThreshold: Int): String {
    return if (count > countThreshold) {
        "I have answer"
    } else {
        "Answer eludes me"
    }
}

// Replace return keyword with assignment operator
fun generateAnswerStringAssignment(count: Int, threshold: Int): String = if (count > threshold) {
    "I have answer"
} else {
    "Answer eludes me"
}

// Anonymous functions
val stringLengthFunc: (String) -> Int = {s -> s.length}

// Higher-order functions : A function can take another function in the argument
fun stringMapper(str: String, block: (String) -> Int): Int {
    // Invoke function
    return block(str)
}

fun main(args: Array<String>) {
    println(generateAnswerString(5, 3))
    println(generateAnswerStringAssignment(3, 5))

    println("Length of \"Android-Kotlin\" = ${stringLengthFunc("Android Kotlin")}")

    println(stringMapper("Android", { input -> input.length }))

    // If the anonymous function is the last parameter defined on a function,
    // you can pass it outside of the parentheses used to invoke the function,
    // as shown in the following example:
    val lengthyString = stringMapper("Android-Kotlin") { input -> input.length}
    println("length of \"Android-Kotlin\" from higher order functions = ${lengthyString}")

}
