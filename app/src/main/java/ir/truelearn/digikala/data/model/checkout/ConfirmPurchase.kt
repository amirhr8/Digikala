package ir.truelearn.digikala.data.model.checkout

data class ConfirmPurchase(
    val orderId: String,
    val token: String,
    val transactionId: String
)
