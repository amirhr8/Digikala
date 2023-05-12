package ir.truelearn.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.navigation.Screen
import ir.truelearn.digikala.ui.screens.basket.BuyProcessContinue
import ir.truelearn.digikala.ui.screens.basket.CartPriceDetailSection
import ir.truelearn.digikala.util.Constants
import ir.truelearn.digikala.viewmodel.BasketViewModel
import ir.truelearn.digikala.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    val cartDetail by basketViewModel.cartDetail.collectAsState()
    val currentCartItems by basketViewModel.ourCartItems.collectAsState()


    var shippingCost by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }


    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()
    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CheckoutScreen error : ${shippingCostResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn() {
            item { CheckoutTopBarSection(navController) }
            item {
                CartAddressSection(navController) { addressList ->
                    if(addressList.isNotEmpty()){
                        checkoutViewModel.getShippingCost(addressList[0].address)
                    }else{
                        checkoutViewModel.getShippingCost("")
                    }
                }
            }
            item { CartItemReviewSection(shippingCost, cartDetail, currentCartItems) }
            item { CartInfoSection() }
            item { CartPriceDetailSection(cartDetail, shippingCost) }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BuyProcessContinue(cartDetail.payablePrice, shippingCost) {

            }
        }
    }

}