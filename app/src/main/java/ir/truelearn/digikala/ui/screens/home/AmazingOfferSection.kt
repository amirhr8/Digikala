package ir.truelearn.digikala.ui.screens.home

import android.util.Log
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.viewmodel.HomeViewModel

@Composable
fun AmazingOfferSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var amazingItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val amazingItemResult by viewModel.amazingItems.collectAsState()
    when (amazingItemResult) {
        is NetworkResult.Success -> {
            amazingItemList = amazingItemResult.data ?: emptyList()
            loading = false
            Log.e("3636", "item : ${amazingItemList[0].name}")
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "AmazingOfferSection error : ${amazingItemResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

}