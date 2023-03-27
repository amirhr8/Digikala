package ir.truelearn.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import ir.truelearn.digikala.R
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import ir.truelearn.digikala.ui.theme.digikalaRed
import ir.truelearn.digikala.ui.theme.spacing
import ir.truelearn.digikala.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BasketScreen(navController: NavHostController) {

    Basket(navController)

}

@Composable
fun Basket(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val currentCartItemsCount = remember {
        mutableStateOf(0)
    }
    val nextCartItemsCount = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(true) {
        viewModel.currentCartItemsCount.collectLatest { count ->
            currentCartItemsCount.value = count
        }
    }
    LaunchedEffect(true) {
        viewModel.nextCartItemsCount.collectLatest { count ->
            nextCartItemsCount.value = count
        }
    }

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val tabTitles = listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list)
    )

    Column {

        TabRow(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium),
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colors.digikalaRed,
            backgroundColor = Color.White,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(Color.Red)

                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = (selectedTabIndex == index),
                    onClick = {
                        selectedTabIndex = index
                    },
                    selectedContentColor = MaterialTheme.colors.digikalaRed,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold,
                            )

                            val cartCounter = if (index == 0) {
                                currentCartItemsCount.value
                            } else {
                                nextCartItemsCount.value
                            }
                            if (cartCounter > 0) {
                                Spacer(modifier = Modifier.width(10.dp))
                                SetBadgeToTab(
                                    selectedTabIndex = selectedTabIndex,
                                    index = index,
                                    cartCounter = cartCounter
                                )
                            }

                        }
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ShoppingCart()
            1 -> NextShoppingList()
        }

    }

}

