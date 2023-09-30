package ir.truelearn.digikala.ui.screens.product_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AllProductCommentsScreen(
    navController: NavHostController,
    productId: String
) {
    Text(text = productId)
}