package com.mr.bootcamp.karatpaypal

data class Transaction(
    val userId: String,
    val amount: Int,
    val timeStamp : Int
)
