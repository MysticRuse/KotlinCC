package generics

class Pair<K, V> (
    var key: K, val value: V
)

fun <K, V> printPair(pair: Pair<K, V>) {
    println("${pair.key} ->  ${pair.value}")
}

class Container<T> {
    val list: MutableList<T> = mutableListOf()

    fun addItem(item: T) {
        list.add(item)
    }
}

fun main(args: Array<String>) {
    val pair1 = Pair(2, 4)
    val pair2 = Pair("Name", "Gabriela")
    val pair3 = Pair("Age", 25)
    printPair(pair1)
    printPair(pair2)
    printPair(pair3)

    val container = Container<Int>()
    container.addItem(3)
    println("Container size: ${container.list.size}")
    container.addItem(4)
    println("Container: ${container.list}, Container size: ${container.list.size}")
}


