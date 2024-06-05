package ir.truelearn.digikala.ui.screens.checkout

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ir.truelearn.digikala.R
import ir.truelearn.digikala.data.model.checkout.ConfirmPurchase
import ir.truelearn.digikala.data.model.purchase.PaymentRequest
import ir.truelearn.digikala.data.model.purchase.PaymentVerificationRequest
import ir.truelearn.digikala.navigation.Screen
import ir.truelearn.digikala.ui.theme.digikalaRed
import ir.truelearn.digikala.ui.theme.roundedShape
import ir.truelearn.digikala.ui.theme.spacing
import ir.truelearn.digikala.util.Constants
import ir.truelearn.digikala.util.Constants.ZARINPAL_MERCHANT_ID
import ir.truelearn.digikala.util.Constants.ZARINPAL_PAYMENT_URL
import ir.truelearn.digikala.util.Constants.isFromPurchase
import ir.truelearn.digikala.util.Constants.purchaseOrderId
import ir.truelearn.digikala.util.Constants.purchasePrice
import ir.truelearn.digikala.util.DigitHelper
import ir.truelearn.digikala.viewmodel.BasketViewModel
import ir.truelearn.digikala.viewmodel.CheckoutViewModel
import ir.truelearn.digikala.viewmodel.PurchaseViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConfirmPurchaseScreen(
    navController: NavController,
    orderId: String,
    orderPrice: String,
    purchaseViewModel: PurchaseViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    purchaseOrderId = orderId
    purchasePrice = orderPrice

    val context = LocalContext.current

    var orderState by remember { mutableStateOf(context.getString(R.string.waiting_for_purchase)) }

    val purchaseResult by purchaseViewModel.purchaseResult.collectAsState(null)
    val verifyPurchaseResult by purchaseViewModel.verifyPurchaseResult.collectAsState(null)


    LaunchedEffect(true) {
        if (isFromPurchase) {
            purchaseViewModel.verifyPurchase(
                PaymentVerificationRequest(
                    merchant_id = ZARINPAL_MERCHANT_ID,
                    authority = Uri.parse(Constants.afterPurchaseUrl).getQueryParameter("Authority")
                        .toString(),
                    amount = orderPrice + "0",
                )
            )
        } else {
            purchaseViewModel.startPurchase(
                PaymentRequest(
                    merchant_id = ZARINPAL_MERCHANT_ID,
                    amount = orderPrice + "0",
                    callback_url = "truelearn://digikala",
                    description = "خرید تستی از دیجی کالا",
                )
            )
        }

    }


    if (purchaseResult != null) {
        purchaseViewModel.openBrowser(
            context = context,
            url = ZARINPAL_PAYMENT_URL + purchaseResult!!.data.authority
        )
    }

    if (verifyPurchaseResult != null) {
        if (verifyPurchaseResult!!.data.message == "Paid") {
            orderState = context.getString(R.string.purchase_is_ok)
            basketViewModel.deleteAllItems()
            checkoutViewModel.confirmPurchase(
                ConfirmPurchase(
                    token = Constants.USER_TOKEN,
                    transactionId = verifyPurchaseResult!!.data.ref_id.toString(),
                    orderId = orderId
                )
            )
            Log.e("3636", "Transaction ID : ${verifyPurchaseResult!!.data.ref_id}")
        }


    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                MaterialTheme.spacing.medium,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.final_price),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = DigitHelper.digitByLocateAndSeparator(orderPrice),
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = orderState,
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_code),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = orderId,
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Button(
            onClick = {
                isFromPurchase = false
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.digikalaRed),
            shape = MaterialTheme.roundedShape.small,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                    ),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colors.digikalaRed,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }

    }


}