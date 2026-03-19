package general

import java.util.Locale

/**
 * “A higher-order function is a function that takes another function as a parameter or returns a function,
 * enabling behavior to be passed as data.”
 */
data class HoFish(var name: String)

fun fishExample() {
    val fish = HoFish("splashy")

    // With is a higher order function
    with (fish.name) {
        println(this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    }

    // Replace with myWith
    myWith(fish.name) {
        println(this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    }

    println("fish.run: ${fish.run { name }}")
    println("fish.apply: ${fish.apply{}}")

    val fish2: HoFish = HoFish(name="splashy").apply { name = "Sharky"}
    println("fish2.name: ${fish2.name}")

    // Using let
    println(fish.let { it ->  it.name.lowercase() }
        .let { it + "fish" }
        .let { it.length }
        .let { it + 31}
        )

}

fun myWith(name: String, block: String.()-> Unit) {
    name.block()
}

//////////////////////////////
// List example
//////////////////////////////

fun listExample() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val divisibleByThree = numbers.divisibleBy {
        it.rem(3)
    }
    println("Divisible by 3 : $divisibleByThree")
}

// Create a higher order function for List using generics.
// For specifics could replace T with Int, Boolean, Char etc
private fun <T> List<T>.divisibleBy(block: (T) -> T): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (block(item) == 0) {
            result.add(item)
        }
    }

    return result
}


enum class Direction {
    NORTH, SOUTH, WEST, EAST, START, END
}

class Game() {
    var path  = mutableListOf(Direction.START)

    val north = { path.add(Direction.NORTH) }
    val south = { path.add(Direction.SOUTH) }
    val west = { path.add(Direction.WEST) }
    val east = { path.add(Direction.EAST) }

    val end = {
        path.add(Direction.END)
        println("Game Over")
        println(path)
        path.clear()
        false
    }

    fun move(where: () -> Boolean) {
        where.invoke()
    }

    fun makeMove(command: String?) {
        if (command.equals("n")) move(north)
        else if (command.equals("s")) move(south)
        else if (command.equals("e")) move(east)
        else if (command.equals("w")) move(west)
        else move(end)
    }
}

fun main(args: Array<String>) {

    listExample()

    fishExample()

    val game = Game()
    // print initial path
    println(game.path)

    // Invoke movements on the lambda function.
    game.north()
    game.south()
    game.west()
    game.east()
    game.end()
    println(game.path)

    /**
     * The above prints:
     * [START]
     * Game Over
     * [START, NORTH, SOUTH, WEST, EAST, END]
     * []
     */

    // Play Game again:
    println()
    println("--------Game starts!-----------")
    while(true) {
        print("Enter a direction: n/s/e/w/: ")
        game.makeMove(readLine())
    }
}