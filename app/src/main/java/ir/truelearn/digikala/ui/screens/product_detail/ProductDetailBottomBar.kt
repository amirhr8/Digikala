package ir.truelearn.digikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import ir.truelearn.digikala.util.DigitHelper
import ir.truelearn.digikala.R
import ir.truelearn.digikala.ui.theme.*
import ir.truelearn.digikala.util.DigitHelper.applyDiscount

@Composable
fun ProductDetailBottomBar(
    item: ProductDetail,
    navController: NavController,
) {

    var price = 0L
    item.price?.let {
        price = it
    }
    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
    }


    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomBar,
        elevation = MaterialTheme.elevation.small,
        modifier = Modifier.height(70.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.biggerSmall,
                    horizontal = MaterialTheme.spacing.medium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                shape = MaterialTheme.roundedShape.small,

                ) {
                Text(
                    text = stringResource(R.string.add_to_basket),
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(
                            vertical = MaterialTheme.spacing.extraSmall,
                        )
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.DigikalaDarkRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically),

                        ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(discountPercent.toString())}%",
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                        )
                    }

                    Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))

                    Text(
                        text = DigitHelper.digitByLocateAndSeparator((price).toString()),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }

                Row() {
                    Text(
                        text = DigitHelper.digitByLocateAndSeparator(applyDiscount(price , discountPercent).toString()),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }
            }

        }


    }


}