package com.mr.bootcamp.karatpaypal

/**
 * Payment Dependency Graph (Graph / Topological Sort)
 * Scenario:
 * Some payments depend on other payments being confirmed first.
 * Problem:
 * Determine an order to process payments or detect a cycle (invalid dependency).
 * data class Payment(val id: String, val dependencies: List<String>)
 * Skills Tested:
 * Graph modeling
 * Topological sort / cycle detection
 * Kotlin map & set usage
 * Staff-Level Follow-up:
 * Discuss thread-safe processing for parallel payment execution
 * Optimize graph representation for millions of payments
 */

/**
 * Topological sort using queue
 * Detect cycles
 * Idiomatic kotlin : getOrPut(), ArrayDequeue()
 */

fun findPaymentOrder(payments: List<Payment>): List<String> {
    // Map to create the dependency graph
    val graph = mutableMapOf<String, MutableList<String>>()

    // Map to count indegrees
    val indegrees = mutableMapOf<String, Int>()

    // Create the dependency graph and fill in the indegree values based on payments list
    for (p in payments) {
        graph.getOrPut(p.id) { mutableListOf() }
        indegrees.putIfAbsent(p.id, 0)
        for (dep in p.dependencies) {
            graph.getOrPut(dep) { mutableListOf() }.add(p.id)
            indegrees[p.id] = indegrees.getOrDefault(p.id, 0) + 1
        }
    }

    println("Graph: $graph")
    println("Indegrees: $indegrees")

    // initialize a queue
    val queue = ArrayDeque<String>()

    // All payments with indegrees zeros, add to the queue
    indegrees.filter { it.value == 0}.forEach { queue.add(it.key) }

    val order = mutableListOf<String>()
    // Iterate through the queue until empty, reduce indegrees for each payment as you traverse the dependency graph.
    // if indegree 0, add it back to queue.

    while(queue.isNotEmpty()) {
        val node = queue.removeFirst()
        order.add(node)

        for (next in graph[node] ?: emptyList()) {
            indegrees[next] = indegrees[next]!! - 1
            if (indegrees[next] == 0) {
                // Add back next to queue for order fulfilment
                queue.add(next)
            }
        }
    }

    return order
}

fun main(args: Array<String>) {

    println("\n========== Problem 5: Payment Dependency Graph ==========")
    testProblem5()

}

fun testProblem5() {
    val payments = listOf(
        Payment("A", listOf()),
        Payment("B", listOf("A")),
        Payment("C", listOf("B")),
        Payment("D", listOf("A"))
    )

    println(payments)

    val order = findPaymentOrder(payments)
    println("Payment processing order: $order")
}

/**
 * Problem 5 output with above result for visualization
 * ========== Problem 5: Payment Dependency Graph ==========
 * [Payment(id=A, dependencies=[]), Payment(id=B, dependencies=[A]), Payment(id=C, dependencies=[B]), Payment(id=D, dependencies=[A])]
 * Graph: {A=[B, D], B=[C], C=[], D=[]}
 * Indegrees: {A=0, B=1, C=1, D=1}
 * Payment processing order: [A, B, D, C]
 */