package generics

/**
 * Generics = Type-safe code -works with multiple types without sacrificing compile time type checks
 *  - Reusable code across different types.
 *  - Compiler-time type safety
 *  - Eliminate type casting
 *
 *  Type parameters make code type-safe and reusable.
 *  Use angle brackets to declare type parameters
 *  Kotlin can infer type parameters automatically.
 *  Both classes and functions can be generic.
 */


sealed class Either<out F, out S> {

    data class Success<S>(val value: S) : Either<Nothing, S>()
    data class Fail<F>(val error: F): Either<F, Nothing>()

    val isSuccess: Boolean get() = this is Success<S>
    val isFail: Boolean get() = this is Fail<F>
}


class Box<T>(val item: T) {
    fun get(): T = item
}

// Generic Functions
fun <T> printItem(item: T) {
    println(item)
}

fun <T> identity(value: T): T {
    return value
}

fun doLearnGenericFunctions() {
    printItem(100)
    printItem("Hello World")

    val result = identity(42)
    printItem(result)
}

fun main(args: Array<String>) {

    val intBox = Box(12).get()

    // Explicit type parameter with <String> - not necessary.
    val stringBox = Box<String>("Hello World").get()

    // Kotlin can do type inference based on the parameter
    val stringBoxInfer = Box("Hello World Infer").get()

    println("intBox: $intBox, stringBox: $stringBox, stringBoxInfer: $stringBoxInfer")

    println("------------------------------")
    doLearnGenericFunctions()

    val success = Either.Success("OK")
    val fail = Either.Fail(error = IllegalStateException("OKNot").message)
    1
}