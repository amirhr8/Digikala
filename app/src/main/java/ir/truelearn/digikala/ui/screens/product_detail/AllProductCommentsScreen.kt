package ir.truelearn.digikala.ui.screens.product_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ir.truelearn.digikala.viewmodel.ProductDetailViewModel

@Composable
fun AllProductCommentsScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navController: NavHostController,
    productId: String
) {

    val commentsList = viewModel.commentsList.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(Modifier.fillMaxSize()) {
            items(commentsList) { comments->

                comments?.forEach{ comment->
                    Text(
                        text = comment.title,
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                    )
                }

            }
        }


    }



}