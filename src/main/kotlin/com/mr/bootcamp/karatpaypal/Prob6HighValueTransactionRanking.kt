package com.mr.bootcamp.karatpaypal

import java.util.PriorityQueue

/**
 * High-Value Transaction Ranking (Heap + Sorting)
 * Scenario:
 * You need to display top N transactions in a user’s feed efficiently.
 * Problem:
 * Given a stream of transactions, return the top N by amount.
 * Skills Tested:
 * Min-heap for top N
 * Kotlin PriorityQueue + comparator
 * Streaming / incremental updates
 * Staff-Level Follow-up:
 * Memory-efficient streaming
 * Coroutines + Channels for live feed
 * Avoid sorting entire dataset repeatedly
 */
fun topNTransactions(transactions: List<Transaction>, N: Int): List<Transaction> {

    // Min Heap to find top N
    val heap = PriorityQueue<Transaction>( compareBy { it.amount})

    for (txn in transactions) {
        heap.add(txn)
        if (heap.size > N ) heap.poll()
    }
    return heap.sortedByDescending { it.amount }
}

fun main(args : Array<String>) {
    println("\n========== Problem 6: Top N Transactions ==========")
    testProblem6()
}



fun testProblem6() {
    val transactions = listOf(
        Transaction("u1", 500, 100),
        Transaction("u2", 200, 200),
        Transaction("u3", 800, 300),
        Transaction("u4", 300, 400)
    )

    val top2 = topNTransactions(transactions, 2)
    println("Top 2 transactions:")
    top2.forEach {
        println("${it.userId} → ${it.amount}")
    }
}

