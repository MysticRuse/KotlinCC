package practice.karatpaypal

/**
 * Given a list of key-card swipe events (like LeetCode 1604) for multiple users:
 * data class Swipe(val userId: String, val time: String)
 * Problem:
 * Alert users who swipe ≥ 3 times in 60 minutes. Return sorted list of userIds.
 * Skills Tested:
 * Sorting + grouping
 * Sliding window pattern
 * Kotlin idiomatic: map, sequences
 * Staff-Level Follow-up:
 * Discuss memory limits for millions of users
 * Streaming vs batch processing
 * Optimize for main-thread safety in Android
 */

/**
 * Sliding window, Sorting per user
 * Idiomatic Kotlin: getOrPut(), map, sorted
 */
fun prob3FraudDetection(swipes: List<Swipe>): List<String> {
    // Map userId -> [timestamps]
    val map = mutableMapOf<String, MutableList<Int>>()

    // Convert hh:min -> mins
    fun toMinutes(time: String) = time.split(":").map { it.toInt() }.let { it[0]*60 + it[1] }

    // Add to map
    for(swipe in swipes) {
        map.getOrPut(swipe.userId) { mutableListOf() }.add(toMinutes(swipe.timestamp))
    }

    // For each entry in the map, sort the times for each user and add to alert list
    val result = mutableListOf<String>()
    for ((user, times) in map) {
        // Sort the times
        times.sort()

        // Check each set of 3 time items and if they less than 60 mins - add user to result
        for( i in 2 until times.size) {
            if (times[i] - times[i-2] <= 60) {
                result.add(user)
                //  Break here and check the next user. Don't need to go through all the swipe times
                break
            }
        }
    }

    return result.sorted()
}

fun main(args: Array<String>) {
    println("\n========== Problem 3: Fraud Detection (Key Swipe Alerts) ==========")
    testProblem3()
}

fun testProblem3() {
    val swipes = listOf(
        Swipe("kathy", "11:00"),
        Swipe("kathy", "11:20"),
        Swipe("kathy", "11:40"),
        Swipe("bob", "09:00"),
        Swipe("bob", "10:30"),
        Swipe("bob", "11:45"),
        Swipe("alice", "10:00"),
        Swipe("alice", "10:20"),
        Swipe("alice", "10:40"),
    )

    val alerts = prob3FraudDetection(swipes)
    println("Alerted users: $alerts")
}

