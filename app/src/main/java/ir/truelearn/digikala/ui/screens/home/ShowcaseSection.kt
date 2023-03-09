package ir.truelearn.digikala.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.truelearn.digikala.R
import ir.truelearn.digikala.navigation.Screen
import ir.truelearn.digikala.ui.components.RoundedIconBox
import ir.truelearn.digikala.ui.theme.LocalSpacing
import ir.truelearn.digikala.ui.theme.amber
import ir.truelearn.digikala.ui.theme.grayCategory

@Composable
fun ShowcaseSection(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.semiMedium,
                vertical = LocalSpacing.current.biggerSmall
            )
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RoundedIconBox(
                image = painterResource(id = R.drawable.digijet),
                title = stringResource(id = R.string.digikala_jet),
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.digikalajet.com/user/address"
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.auction),
                title = stringResource(id = R.string.digi_style),
                onClick = onBoxClick(
                    navController = navController,
                    url = "https://www.digistyle.com/sale-landing/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=style&promo_name=style&promo_position=circle_badge"
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.digipay),
                title = stringResource(id = R.string.digi_pay),
                onClick = onBoxClick(
                    navController,
                    url ="https://www.digikala.com/my-digipay/?promo_name=my-digipay&promo_position=circle_badge"),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.pindo),
                title = stringResource(id = R.string.pindo),
                bgColor = MaterialTheme.colors.amber,
                onClick = onBoxClick(
                    navController,
                    url ="https://www.pindo.ir/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=pindo&promo_name=pindo&promo_position=circle_badge"),
            )

        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RoundedIconBox(
                image = painterResource(id = R.drawable.shopping),
                title = stringResource(id = R.string.digi_shopping),
                onClick = {},
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.giftcard),
                title = stringResource(id = R.string.gift_card),
                onClick = onBoxClick(
                    navController,
                    url ="https://www.digikala.com/landing/gift-card-landing/?promo_name=gift_landing&promo_position=circle_badge"),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.digiplus),
                title = stringResource(id = R.string.digi_plus),
                onClick = onBoxClick(
                    navController,
                    url ="https://www.digikala.com/plus/landing/?promo_name=plus&promo_position=circle_badge"),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.more),
                title = stringResource(id = R.string.more),
                bgColor = MaterialTheme.colors.grayCategory,
                onClick = {},
            )

        }

    }

}


@Composable
fun onBoxClick(navController: NavController, url: String): () -> Unit =
    { navController.navigate(route = Screen.WebView.route + "?url=${url}") }
