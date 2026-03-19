package com.mr.bootcampclass SimpleSpice() {
    val name = "curry"
    val spiciness = "mild"
    val heat: Int
        get() {
            // Spiciness of mild is 5
            return 5
        }
}

public fun main(args: Array<String>) {
    val ss = SimpleSpice()
    println("Spice is ${ss.name} ${ss.heat}")
}