package practice.karatpaypal

data class Transaction(
    val userId: String,
    val amount: Int,
    val timeStamp : Int
)

data class TransactionP(
    val userId: String,
    val amount: Int,
    val timeStamp: Int,
)
