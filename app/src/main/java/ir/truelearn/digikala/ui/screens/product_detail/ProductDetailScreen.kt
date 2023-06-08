package ir.truelearn.digikala.ui.screens.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {

    var productDetailList by remember {
        mutableStateOf<ProductDetail>(ProductDetail())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.getProductById(productId)
        viewModel.productDetail.collectLatest { productDetail ->
            when (productDetail) {
                is NetworkResult.Success -> {
                    productDetailList = productDetail.data!!
                    productDetailList.name?.let { Log.e("3636", it) }
                    loading = false
                }
                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${productDetail.message}")
                }
                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }


    Scaffold(
        bottomBar = {
            ProductDetailBottomBar(productDetailList , navController)
        }
    ) {
        LazyColumn(){

        }
    }

    Text(text = productId)


}