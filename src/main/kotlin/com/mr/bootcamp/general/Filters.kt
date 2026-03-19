package com.mr.kotlin.bootcamp.general

/**
 * “Sequences are lazy until a terminal operation triggers evaluation.”
 * For sequences, nothing runs until a terminal operation is called. Terminal Operations:
 * toList()
 * first()
 * any()
 * count()
 * forEach()
 */
/**
 * “Some operations like sorting are inherently eager even when used with sequences.”
 * users
 *     .asSequence()
 *     .sortedBy { it.age } // ❗ eager step
 *     .map { it.name }
 *     .toList()
 * 📌 sortedBy must see all elements, so it’s eager even in sequences.
 */
fun eagerExample() {
    val decorations = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

    val eager = decorations.filter { it[0] == 'p'}
    println("Eager decorations: $eager")

    // Apply filter lazily
    // asSequence converts a kotlin collection (like List) into a Sequence
    // Collections operations -> eager ( do work immediately and completely)
    // Sequence operations -> lazy ( processes elements one ata time only when needed.)
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    println("Filtered: $filtered")
    println("Filtered toList(): ${filtered.toList()}")

    // Apply map lazily
    val lazyMap = decorations.asSequence().map {
        println("map: $it")
        it
    }

    println(lazyMap)
    println("first: ${ lazyMap.first()}")
    println("all: ${lazyMap.toList()}")

}

fun main(args: Array<String>) {
    eagerExample()
}

/**
 * "asSequence() turns eager collection operations into lazy pipelines,
 *  - allowing element-by-element processing,
 *  - fewer allocations,
 *  - and early termination."
 *
 * Does asSequence() Improve Performance?
 * ✅ YES when:
 * Large lists
 * Multiple chained operations
 * Early termination (take, first, any)O
 * Heavy transformations
 * ❌ NO when:
 * Very small lists
 * Single operation
 * UI thread micro-optimizations
 * 📌 Interview answer:
 * “Sequences reduce allocations but add iterator overhead, so I use them judiciously.”
 *
 * ❓ Does asSequence() run on background thread?
 * ❌ No. It’s synchronous. Thread depends on where you call it.
 * ❓ Is map lazy by default?
 * ❌ No. Only lazy when operating on a Sequence.
 * ❓ Sequence vs Flow?
 * Sequence: synchronous, CPU-bound
 * Flow: asynchronous, coroutine-based
 */