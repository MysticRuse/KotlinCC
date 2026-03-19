package com.mr.kotlin.bootcamp.general

class SealedClass {
}

sealed class Shape {
    abstract fun area(): Double
    class Circle(val radius: Double) : Shape() {
        override fun area(): Double = radius * radius * Math.PI
    }

    class Rectangle(val width: Int, val height: Int) : Shape() {
        override fun area(): Double  = width * height.toDouble()
    }
}

fun main() {
    val shape : Shape = Shape.Circle(2.0)
    println(shape.area())
}