package com.mr.bootcamp.karatpaypal

import java.util.PriorityQueue

/**
 * Concurrent Payment API Response Merging (Heap + PriorityQueue)
 * Scenario:
 * You fetch transaction streams from 3 different payment processors asynchronously. Each stream returns sorted transactions by timestamp.
 * Problem:
 * Merge all 3 streams into a single timestamp-sorted transaction list efficiently.
 * Constraints:
 * Total transactions: 10^6
 * Use Kotlin idiomatic code
 * Skills Tested:
 * Min-heap / PriorityQueue
 * Merge k sorted arrays
 * Efficient memory use
 * Staff-Level Follow-up:
 * Explain lazy sequences to avoid loading all 10^6 in memory
 * Handle streaming API with coroutines
 */

/**
 * Time Complexity
 *
 * O(n log k) where n = total transactions, k = number of streams
 * Each of n transactions: log k to insert/remove from heap
 */
fun mergeSortedStreams(streams: List<List<Transaction>>): List<Transaction> {

    data class IndexedTransaction(
        val index: Int,
        val transaction: Transaction
    )

    val heap = PriorityQueue<IndexedTransaction>(compareBy {it.transaction.timeStamp})
    val iterators = streams.map { it.iterator() }

    // Initialize heap with first element from each stream
    iterators.forEachIndexed{ index, iterator ->
        if (iterator.hasNext()) {
            heap.add(IndexedTransaction(index, iterator.next()))
        }
    }

    val merged = mutableListOf<Transaction>()

    while (heap.isNotEmpty()) {
        val (streamIndex, txn) = heap.poll()
        merged.add(txn)

        // Advance the correct iterator
        if (iterators[streamIndex].hasNext()) {
            heap.add(IndexedTransaction(streamIndex, iterators[streamIndex].next()))
        }
    }

    return merged
}

fun main(args: Array<String>) {
    println("\n========== Problem 2: Merge Sorted Transaction Streams ==========")
    testProblem2()

}

fun testProblem2() {
    val stream1 = listOf(
        Transaction("u1", 10, 100),
        Transaction("u1", 20, 300)
    )
    val stream2 = listOf(
        Transaction("u2", 15, 200),
        Transaction("u2", 25, 400)
    )
    val stream3 = listOf(
        Transaction("u3", 5, 150),
        Transaction("u3", 30, 500)
    )

    val merged = mergeSortedStreams(listOf(stream1, stream2, stream3))
    merged.forEach {
        println("${it.userId} → ${it.amount} @ ${it.timeStamp}")
    }
}