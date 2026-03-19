package com.mr.bootcamp.karatpaypal

/**
 * Account Balance Range Queries (Segment Tree / Prefix Sum)
 * Scenario:
 * You have daily balance changes for a user over a month.
 * Problem:
 * Support queries:
 * “What is the maximum balance between day i and day j?”
 * Skills Tested:
 * Prefix sum / segment tree
 * Efficient range queries
 * Kotlin data structures
 * Staff-Level Follow-up:
 * Discuss handling multiple users concurrently
 * Mutable vs immutable data structures
 */

fun maxBalanceInRange(balances: List<Int>, startDay: Int, endDay: Int) : Int {

    var maxBalance = 0
    for (day in startDay-1..< endDay) {
        maxBalance = maxOf(maxBalance, balances[day])
    }
    return maxBalance
}

/**
 * Prefix Array for efficient queries.
 * O(1) per query of precomputed segment tree is used.
 * Kotlin idiomatic: slice, maxOrNull
 */
fun maxBalanceInRangePrefixArray(balances: List<Int>, start: Int, end: Int) : Int {
    val prefix = IntArray(balances.size + 1)
    for (i in balances.indices) prefix[i+1] = maxOf(prefix[i], balances[i])
    return prefix.slice(start..end).maxOrNull() ?: 0
}

fun main(args: Array<String>) {
    println("\n========== Problem 4: Account Balance Range Query ==========")
    testProblem4()
}

fun testProblem4() {
    val balances = listOf(100, 200, 150, 300, 250)
    val maxBalance = maxBalanceInRange(balances, 1, 4)
    println("Max balance between index 1 and 4: $maxBalance")

    val maxBalancePrefix = maxBalanceInRangePrefixArray(balances, 1, 4)
    println("Max balance with prefixArray between index 1 and 4: $maxBalancePrefix")
}


