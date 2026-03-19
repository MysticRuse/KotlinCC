package com.mr.kotlin.bootcamp.general

fun conditionalsIfELse(count: Int) {
    if (count == 42) println("Answer : $count")
    else println("Answer eludes me")
}

fun conditionalsIfELseIf(count: Int) {
    if (count == 42) println("Answer : $count")
    else if (count > 35) println("Answer $count is close")
    else println("Answer $count eludes me")
}

fun conditionalExpressions(count: Int) {
    val answerString: String = if (count == 42) {
        "Answer: $count"
    } else if (count > 35) {
        "Answer $count is close"
    } else {
        "Answer $count eludes me"
    }

    println(answerString)
}

fun conditionalWhen(count: Int) {
    val answerString = when {
        count == 42 -> "I have the answer. It is $count"
        count > 35 -> "The answer $count is close"
        else -> "The answer $count eludes me"
    }

    println(answerString)
}

fun conditionalsSmartCasting() {
    val languageName: String? = null
    // Check if variable contains a reference toa null value using a conditional statement.
    if (languageName != null) {
        println(languageName.uppercase())
    }
}


