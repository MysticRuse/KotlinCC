package com.mr.bootcamp.karatpaypal

import kotlin.collections.iterator

private const val ONE_HOUR_IN_SECONDS = 60 * 60


/**
 * You have a list of transactions for multiple users:
 * data class Transaction(val userId: String, val amount: Int, val timestamp: Long)
 * Problem:
 * For each user, return the maximum sum of transactions in any 1-hour window.
 * Constraints:
 * Up to 10^5 transactions
 * Timestamps may not be sorted
 * Skills Tested:
 * - HashMap grouping
 * - Sliding window on sorted timestamps
 * - Kotlin idiomatic collections
 */
/**
 * Sliding window pattern
 * Efficient O(N log N) per user (sorting)
 * Idiomatic Kotlin: groupBy, maxOf
 */
fun MaxTransactionsPerUser(transactions: List<Transaction>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    // Group transactions by user
    val grouped = transactions.groupBy {it.userId}

    for((user, txns) in grouped) {
        val sortedTxns = txns.sortedBy {it.timeStamp}

        var maxSum = 0
        var start = 0
        var windowSum = 0

        // Sliding window
        for (end in sortedTxns.indices) {
            windowSum += sortedTxns[end].amount
            while (sortedTxns[end].timeStamp - sortedTxns[start].timeStamp > ONE_HOUR_IN_SECONDS) {
                windowSum -= sortedTxns[start].amount
                start++
            }
            maxSum = maxOf(windowSum, maxSum)
        }

        result[user] = maxSum
    }

    return result
}

fun main(args: Array<String>) {
    val transactions: List<Transaction> = listOf(
        Transaction("user1", 50, 1000),
        Transaction("user1", 30, 1200),
        Transaction("user1", 20, 1500),
        Transaction("user1", 900, 5100),
        Transaction("user2", 100, 1100),
        Transaction("user2", 200, 1600),
        Transaction("user2", 50, 1650),
        Transaction("user2", 50, 1700),
        Transaction("user2", 500, 8600)
    )

    val result = MaxTransactionsPerUser(transactions)

    // Print results
    for ((user, maxSum) in result) {
        println("User: $user, Max sum in 1-hour window: $maxSum")
    }

}



