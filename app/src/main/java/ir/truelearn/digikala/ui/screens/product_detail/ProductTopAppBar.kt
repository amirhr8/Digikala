package ir.truelearn.digikala.ui.screens.product_detail

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import ir.truelearn.digikala.R
import ir.truelearn.digikala.data.model.product_detail.Price
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import ir.truelearn.digikala.navigation.Screen
import ir.truelearn.digikala.ui.theme.darkText
import ir.truelearn.digikala.ui.theme.spacing
import ir.truelearn.digikala.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun ProductTopAppBar(navController: NavHostController, product: ProductDetail) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(0.6f),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.exit),
                    contentDescription = "",
                    modifier = Modifier.size(17.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }
        }

        Row(
            modifier = Modifier.weight(0.4f),
        ) {

            IconButton(onClick = {
                navController.navigate(Screen.Basket.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            var checkedState by remember { mutableStateOf(false) }
            IconToggleButton(
                checked = checkedState,
                onCheckedChange = {
                    checkedState = !checkedState
                }
            ) {
                val transition =
                    updateTransition(checkedState, label = "icon transition")
                val tint by transition.animateColor(label = "iconColor") { isCheck ->
                    if (isCheck) Color.Red else MaterialTheme.colors.darkText
                }
                Icon(
                    imageVector = if (checkedState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier
                        .size(27.dp)
                )
            }


            var expanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    expanded = true
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_dots),
                    contentDescription = "",
                    modifier = Modifier.size(27.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colors.surface)
            ) {
                DropdownMenuItem(
                    onClick = {
                        val priceListString = Gson().toJson(product.priceList)
                        expanded = false
                        navController.navigate(
                            Screen.ProductPriceChart.route + "?jsonString=${priceListString}"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chart),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(R.string.price_chart),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        shareToSocialMedia(
                            context,
                            product.name!!,
                            digitByLocateAndSeparator(product.price!!.toString()),
                            "https://truelearn.ir/"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(R.string.share_product),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }
            }

        }


    }
}

private fun shareToSocialMedia(
    context: Context,
    productName: String,
    productPrice: String,
    url: String
) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "$productName با قیمت باورنکردنی $productPrice تومان فقط در فروشگاه زیر \n $url"
    )

    context.startActivity(Intent.createChooser(shareIntent , "share to..."))
}

