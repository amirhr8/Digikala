package ir.truelearn.digikala.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import ir.truelearn.digikala.data.model.category.Sub
import ir.truelearn.digikala.ui.theme.darkText
import ir.truelearn.digikala.ui.theme.grayCategory
import ir.truelearn.digikala.ui.theme.roundedShape
import ir.truelearn.digikala.ui.theme.spacing
import ir.truelearn.digikala.R
import ir.truelearn.digikala.navigation.Screen
import ir.truelearn.digikala.util.DigitHelper.digitByLocate

@Composable
fun SubCategoryItem(
    navController: NavHostController,
    item: Sub
) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.extraSmall
            )
            .clickable {
                navController.navigate(Screen.SubCategoryScreen.withArgs(item._id))
            }
        ,
        shape = MaterialTheme.roundedShape.small
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.grayCategory)
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.semiMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "product image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = item.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.h6,
                text = "+${digitByLocate(item.count.toString())} ${stringResource(id = R.string.commodity)}"
            )
        }

    }
}