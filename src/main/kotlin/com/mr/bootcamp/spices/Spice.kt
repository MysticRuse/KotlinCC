package com.mr.bootcamp.spices

/**
 * Types pof classes:
 * Class
 * Abstract class
 * Data class
 * Singleton class
 * enum Class
 * sealed class // can be subclassed only in the same class file. static. compile time.
 */
abstract class Spice(val name: String, var spiciness: String = "mild", color: SpiceColor): Any() {

    abstract fun prepareSpice()

    constructor(name: String, heat: Int, color: SpiceColor) : this(
        name,
        spiciness = when(heat) {
            1 -> "no spice"
            2 -> "bland"
            3 -> "salty"
            5 -> "mild"
            7 -> "hot"
            else -> "no spice"
        },
        color,
    )

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

interface Grinder {
    fun grind()
}

interface SpiceColor {
    val color: String
}

class Curry(name: String, spiciness: String, color: SpiceColor = YellowSpiceColor):
        Spice(name, spiciness, color,),
        Grinder,
        SpiceColor by YellowSpiceColor {

    override fun prepareSpice() {
        println("prepareSpice")
    }

    override fun grind() {
        println("grindSpice")
    }

    // Do not need to override color as all Curry will be by YelloSpiceColor
}

object YellowSpiceColor: SpiceColor {
    override val color = "yellow"
}

fun main(args: Array<String>) {
    val cur = Curry("allspice", "mild")
    println(cur.color)

    val spiceCabinet = listOf(
        SpiceContainer(cur),
        SpiceContainer(Curry("Red Curry", "mild")),
        SpiceContainer(Curry("Green Curry", "hot"))
    )

    println(" --- My spice cabinet ---")
    for (spiceCabin in spiceCabinet) {
        println(spiceCabin.label)
    }
}