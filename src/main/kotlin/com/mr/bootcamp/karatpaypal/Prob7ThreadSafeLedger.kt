package com.mr.bootcamp.karatpaypal

import java.util.concurrent.ConcurrentHashMap

/**
 * Immutability & Concurrency
 * Implement a thread-safe ledger using Kotlin immutable data structures or ConcurrentHashMap
 * Show idiomatic Kotlin for safe updates in multi-threaded environment
 */

/**
 * Highlights:
 * ConcurrentHashMap for thread safety
 * Idiomatic Kotlin + Java interop
 * Immutability per transaction
 */
class Prob7ThreadSafeLedger {

    private val balances = ConcurrentHashMap<String, Int>()

    fun deposit(userId: String, amount: Int) {
        balances.merge(userId, amount) { old, new -> old + new}
    }

    fun getBalance(userId: String): Int {
        return balances.getOrDefault(userId, 0)
    }

    fun withdraw(userId: String, amount: Int) {
        balances.merge(userId, amount) { old, new -> old - new}
    }
}

fun main(args: Array<String>) {
    println("\n========== Problem 7: Thread-Safe Ledger ==========")
    testProblem7()
}

fun testProblem7() {
    val ledger = Prob7ThreadSafeLedger()

    ledger.deposit("user1", 100)
    ledger.deposit("user1", 50)
    ledger.deposit("user2", 200)

    println("User1 balance: ${ledger.getBalance("user1")}")
    println("User2 balance: ${ledger.getBalance("user2")}")

    val withdrawAmt = 100
    ledger.withdraw("user1", withdrawAmt)
    println("User1 balance: ${ledger.getBalance("user1")} after withdrawing $withdrawAmt")
}
