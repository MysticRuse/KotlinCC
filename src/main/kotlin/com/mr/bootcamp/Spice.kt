package com.mr.bootcamp

/**
 * Class to learn using constructors
 */
class Spice(name: String, var spiciness: String = "mild"): Any() {

    constructor(name: String, heat: Int) : this(name, spiciness = when(heat) {
        1 -> "no spice"
        2 -> "bland"
        3 -> "salty"
        5 -> "mild"
        7 -> "hot"
        else -> "no spice"
    })

    val heat: Int
        get() {
            return when(spiciness) {
                "mild" -> 5
                "bland" -> 2
                "hot" -> 7
                "salty" -> 3
             else -> { 0 }
            }
        }

    // There can be as many init functions as needed. Will be executed in the order they are defined.
    // Any field/variable referenced in any init block must be defined in a prior init block.
    init {
        println("$name is $spiciness with heat $heat")
    }
}

fun makeSalt() : Spice {
    return Spice("Salt", "salty")
}

fun makeChillyPowder(): Spice {
    return Spice("Chilly Powder", "hot")
}

fun main(args: Array<String>) {
    // Using the class parameters
    makeSalt()
    makeChillyPowder()

    // Using the constructor
    val mySpice = Spice("CuminPowder", 5)
}