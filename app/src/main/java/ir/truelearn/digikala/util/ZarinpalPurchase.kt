package ir.truelearn.digikala.util

import android.app.Activity
import android.util.Log
import com.zarinpal.ZarinPalBillingClient
import com.zarinpal.billing.purchase.Purchase
import com.zarinpal.client.BillingClientStateListener
import com.zarinpal.client.ClientState
import com.zarinpal.provider.core.future.FutureCompletionListener
import com.zarinpal.provider.core.future.TaskResult
import com.zarinpal.provider.model.response.Receipt
import ir.truelearn.digikala.util.Constants.ZARINPAL_MERCHANT_ID

object ZarinpalPurchase {

    private val stateListener = object : BillingClientStateListener {
        override fun onClientServiceDisconnected() {
            Log.e("3636", "Billing client Disconnected")
        }

        override fun onClientSetupFinished(state: ClientState) {
        }
    }

    fun purchase(
        activity: Activity,
        amount: Long,
        description: String,
        onPurchaseCompleted: (String) -> Unit
    ) {

        val client = ZarinPalBillingClient.newBuilder(activity)
            .enableShowInvoice()
            .setListener(stateListener)
            .build()

        val purchase = Purchase.newBuilder()
            .asPaymentRequest(
                ZARINPAL_MERCHANT_ID,
                amount,
                "https://truelearn.ir/",
                description
            ).build()

        client.launchBillingFlow(purchase,
            object : FutureCompletionListener<Receipt> {
                override fun onComplete(task: TaskResult<Receipt>) {
                    if (task.isSuccess) {
                        val receipt = task.success
                        receipt?.transactionID?.let {
                            Log.e("3636", it)
                            onPurchaseCompleted(it)
                        }
                    } else {
                        task.failure?.printStackTrace()
                    }
                }

            }
        )

    }

    fun fakePurchase(
        activity: Activity,
        amount: Long,
        description: String,
        onPurchaseCompleted: (String) -> Unit
    ){
        Thread.sleep(1000)
        onPurchaseCompleted(generateRandomString(8))
    }

    private fun generateRandomString(length: Int):String{
        val chars = ('a'..'z') + ('A'..'Z')+ ('0'..'9')
        return List(length) {chars.random()}.joinToString("")
    }


}