package com.mr.kotlin.bootcamp.general

import java.util.Random

/**
 * Functions are generally named functions - each function has a name.
 * Lambdas: Anonymous Functions or Function Literals in other languages. In kotlin, called lambdas
 *  - it is an expression that makes a function.
 *  - lambdas are written inside curly braces {}
 *  - arguments go on the left side of what is called the function arrow ->
 *  val waterFilter = { dirty -> dirty/2 }
 *
 * Lambdas lead to higher order functions.
 */

var dirty = 20

// lambda function waterFilter takes an Int as argument and returns an Int (Int) -> Int
//   - dirty = 20
//   - val filtered = waterFilter(dirty)
val waterFilter: (Int) -> Int = { dirty -> dirty/2}

fun feedFish(dirty: Int) = dirty + 10

// Main use of lambda functions is with higher order functions.
fun updateDirty(dirty: Int, operation: (Int) -> Int) : Int {
    return operation(dirty)
}

val swim = { println("Fish swimming") }

fun dirtyProcessor() {
    dirty = updateDirty(dirty, waterFilter) // Sending a lambda function as an operand
    println("dirty after waterfilter: $dirty")
    dirty = updateDirty(dirty, ::feedFish)  // Sending a named function as an operand
    println("dirty after feedfish: $dirty")
}

fun quizTask1() {
    // Lambda with no argument
    val rollDice = { Random().nextInt(12) + 1 }

    // Lambda which takes in an argument
    val rollDice0 = { sides: Int ->
        if (sides == 0) 0 else Random().nextInt(sides) + 1
    }

    // Function type notation.
    val rollDice2: (Int) -> Int = { sides: Int ->
        if (sides == 0) 0 else Random().nextInt(sides) + 1 }
}

val rollDice2: (Int) -> Int = { sides: Int ->
    if (sides == 0) 0 else Random().nextInt(sides) + 1 }

fun gamePlay(roll: Int) {
    println("Rolled dice:$roll")
}

data class Fish(val name: String)

val myFishes = listOf(Fish("Flipper"), Fish("Moby Dick"), Fish("DOry"))


fun main(args: Array<String>) {
    swim() // Calling swim like a function.

    println(waterFilter(dirty))
    dirtyProcessor()

    // Quiz question: What is the difference between:
    val random1 =
        Math.random() // has a value assigned at compile time and hence value will not change if random1 accessed multiple time
    val random2 = { Math.random() } // has a lambda assigned at compile time and hence everytime random2 is referenced, it will execute the lambda and generate a new value.
    /**
     * random1 has a value assigned at compile time, and the value never changes when the variable is accessed.
     * random2 has a lambda assigned at compile time, and the lambda is executed every time the variable is referenced, returning a different value.
     */

    gamePlay(rollDice2(12))

    // joinToString() link: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/join-to-string.html
    val filteredFishes = myFishes
            .filter { it.name.contains("i")}
            .joinToString(", ") { it.name }
    println("Filtered fishes: $filteredFishes")


}