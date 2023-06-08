package ir.truelearn.digikala.ui.screens.product_detail

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.truelearn.digikala.R
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import ir.truelearn.digikala.ui.theme.*
import ir.truelearn.digikala.util.DigitHelper
import ir.truelearn.digikala.util.DigitHelper.digitByLocate

@Composable
fun ProductDetailHeaderSection(
    item: ProductDetail
) {

    var category = ""
    item.category?.let {
        category = it
    }

    var name = ""
    item.name?.let {
        name = it
    }
    var star = 0.0
    item.star?.let {
        star = it
    }
    var starCount = 0
    item.starCount?.let {
        starCount = it
    }
    var commentCount = 0
    item.commentCount?.let {
        commentCount = it
    }

    var viewCount = 0
    item.viewCount?.let {
        viewCount = it
    }

    Column {
        Text(
            text = "${stringResource(id = R.string.category)} / $category",
            color = MaterialTheme.colors.DarkCyan,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )

        Text(
            text = name,
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
            maxLines = 2
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colors.Gold
            )
            Text(
                text = digitByLocate(star.toString()),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
            Text(
                text = digitByLocate("($starCount)"),
                color = MaterialTheme.colors.grayAlpha,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )

            Text(
                text = digitByLocate("$commentCount ${stringResource(R.string.user_comments)}"),
                color = MaterialTheme.colors.DarkCyan,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )
            Text(
                text = digitByLocate("$viewCount ${stringResource(R.string.view)}"),
                color = MaterialTheme.colors.DarkCyan,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small,
                )
                .fillMaxWidth()
        ){
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colors.DigikalaLightGreen
            )

            Text(
                text = digitByLocate("90% (80نفر) از خریداران این کالا را پیشنهاد کرده اند."),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
        }
    }
}