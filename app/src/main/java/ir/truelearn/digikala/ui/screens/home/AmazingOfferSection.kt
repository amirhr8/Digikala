package ir.truelearn.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.ui.theme.DigikalaLightRed
import ir.truelearn.digikala.viewmodel.HomeViewModel
import ir.truelearn.digikala.R

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
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "AmazingOfferSection error : ${amazingItemResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.DigikalaLightRed)
    ) {

        LazyRow(modifier = Modifier.background(MaterialTheme.colors.DigikalaLightRed)) {

            item {
                AmazingOfferCard(R.drawable.amazings, R.drawable.box)
            }

            items(amazingItemList) { item ->
                AmazingItem(item = item)
            }

            item {
                AmazingShowMoreItem()
            }


        }

    }

}