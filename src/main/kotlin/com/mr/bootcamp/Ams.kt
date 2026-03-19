package com.mr.bootcamp

import java.util.Calendar
import java.util.Random

fun main(args: Array<String>) {
    feedTheFish()

    val change = shouldChangeWater("Saturday", 22, 20)
    println("---- Should change water ? $change ----")


    // Function call
//    dayOfWeek()
//    accessingArgs(args)
//    expressionsAssigned()
}

fun canAddFish(
    tankSize: Int,
    currentFishList: List<Int>,
    fishSize: Int = 2,
    hasDecorations: Boolean = true
): Boolean {
    return true
}

fun shouldChangeWater(
    day: String,
    temp: Int = 22,
    dirty: Int = 20,
) : Boolean {
    fun isTooHot(temp: Int) = temp > 30
    fun isDirty(dirty:Int) = dirty > 30
    fun isSunday(day: String) = day == "Sunday"

    return when {
        isTooHot(temp) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

fun feedTheFish() {
    println("---- Feed the fish ---")
    val day =  randomDay()
    val food = fishFood(day)
    println("Today is $day, and the fish eats $food")
    shouldChangeWater(day, 20, 50)
    shouldChangeWater(day)
    shouldChangeWater(day, dirty=50)
    println()
}

fun fishFood(day: String) : String {
    var food = "fasting"
    when (day) {
        "Monday" -> food = "flakes"
        "Tuesday" -> food = "pellets"
        "Wednesday" -> food = "redworms"
        "Thursday" -> food = "granules"
        "Friday" -> food = "mosquitoes"
        "Saturday" -> food = "lettuce"
        "Sunday" -> food = "plankton"
    }
    return food
}

fun randomDay() : String {
    val week = listOf("Monday", "Tuesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(week.size)]
}

fun dayOfWeek() {
    println("---- Day of Week ---")
    print("What day is it today? ")
    val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    println(when (day) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> {" Time has stopped"}
    })

    println()
}

fun expressionsAssigned() {
    println("---- Expressions ---")
    val isUnit = println("This is an expression")
    println("is Unit : ${ isUnit } " )

    val temp = 10
    val isHot = if (temp > 50) true else false
    val message = "You are ${ if (isHot) "fried" else "safe" } fish"
    println(message)
    println()
}

fun accessingArgs(args: Array<String>) {
    println("---- Capturing args ---")
    // Capturing args
    println("Hello ${args[0]}")
    println(if (args[1].toInt() < 12) "Good morning ${args[0]}" else "Good night ${args[0]}")
    println()
}

