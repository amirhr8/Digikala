package ir.truelearn.digikala.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.truelearn.digikala.ui.theme.DarkCyan
import ir.truelearn.digikala.ui.theme.spacing
import ir.truelearn.digikala.R
import ir.truelearn.digikala.navigation.Screen
import ir.truelearn.digikala.ui.theme.darkText

@Composable
fun CommentShowMoreItem(
    navController: NavHostController,
    productId: String,
    commentCount: String,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .padding(vertical = MaterialTheme.spacing.medium)
            .clickable {
                navController.navigate(Screen.AllComment.withArgs(productId,commentCount))
            }

    ){
        Icon(
            painter = painterResource(id = R.drawable.show_more),
            contentDescription = "",
            tint = MaterialTheme.colors.DarkCyan,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            text = stringResource(R.string.see_all),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            textAlign = TextAlign.Center,
        )

    }
}