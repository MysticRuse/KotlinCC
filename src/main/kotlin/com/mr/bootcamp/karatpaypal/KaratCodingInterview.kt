package com.mr.bootcamp.karatpaypal

/**
You are working on an inventory system, which lists all items in alphabetical order. We would like to verify that the system is working correctly.

Write a function that takes in a collection of item name strings in an inventory and returns if the list is in alphabetical order. Treat the items as case insensitive.

Example:
inventory1 = ["candy", "Corn", "Peas"]
checkInventory(inventory1) -> True

inventory2 = ["Clock", "Candy", "Corn", "Peas"]
inventory3 = ["Cards", "Card", "Clothes"]
inventory4 = ["Applesauce", "AppleTV", "Television", "Tractor", "Apple"]
inventory5 = ["Candy", "corn", "Peas"]

All Test Cases:
checkInventory(inventory1) -> True
checkInventory(inventory2) -> False
checkInventory(inventory3) -> False
checkInventory(inventory4) -> False
checkInventory(inventory5) -> True

Complexity analysis variables:
I = Number of items
(Note: Individual item strings have constant length)
*/

// Time complexity: O(I) (for loop), Space complexity: O(I) for ol
fun checkInventory(inventory: List<String>) : String {
    val ol = inventory.map { it.lowercase()}

    for ( i in 1 until ol.size) {
        if (ol[i] < ol[i-1]) {
            return "False"
        }
    }
    return "True"
}

fun checkInventoryBrute(myList: List<String>): Boolean {
    // O(IlogI) time, O(I) space
    val ol = myList.map { it.lowercase()}

    // iterate through the ol starting with 1 to n
    // !cehck ol[i] > ol(ol[i-1]) {
    //   return false
    // }

    val sortedL = ol.sorted()

    println("sorted list: $sortedL")
    println("myList list: $myList")

    // O(I) time
    for (i in 0 until ol.size) {
        if (ol[i] != sortedL[i]) return false
    }
    return true
}

fun main(args : Array<String>) {
    val inventory1 = listOf("candy", "Corn", "Peas")
    val inventory2 = listOf("Clock", "Candy", "Corn", "Peas")
    val inventory3 = listOf("Cards", "Card", "Clothes")
    val inventory4 = listOf("Applesauce", "AppleTV", "Television", "Tractor", "Apple")
    val inventory5 = listOf("Candy", "corn", "Peas")

    println(checkInventory(inventory1))
    println(checkInventory(inventory2))
    println(checkInventory(inventory3))
    println(checkInventory(inventory4))
    println(checkInventory(inventory5))

}