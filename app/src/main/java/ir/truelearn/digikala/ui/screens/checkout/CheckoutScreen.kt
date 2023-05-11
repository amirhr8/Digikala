package ir.truelearn.digikala.ui.screens.checkout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.truelearn.digikala.ui.screens.basket.CartPriceDetailSection
import ir.truelearn.digikala.viewmodel.BasketViewModel

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
) {
    val cartDetail by viewModel.cartDetail.collectAsState()
    val currentCartItems by viewModel.ourCartItems.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn() {
            item { CheckoutTopBarSection(navController) }
            item { CartAddressSection(navController) }
            item { CartItemReviewSection("2000", cartDetail, currentCartItems) }
            item { CartInfoSection() }
            item {  CartPriceDetailSection(cartDetail) }
        }
    }

}