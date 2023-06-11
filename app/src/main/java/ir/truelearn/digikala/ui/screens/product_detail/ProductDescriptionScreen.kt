package ir.truelearn.digikala.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.truelearn.digikala.R
import ir.truelearn.digikala.ui.theme.darkText
import ir.truelearn.digikala.ui.theme.searchBarBg
import ir.truelearn.digikala.ui.theme.semiDarkText
import ir.truelearn.digikala.ui.theme.spacing

@Composable
fun ProductDescriptionScreen(
    navController: NavHostController,
    description: String,
) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)

            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.review),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colors.searchBarBg)
        )


        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.semiMedium),
                text = stringResource(id = R.string.product_introduce),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.semiMedium),
                text = description,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText,
            )
        }

    }

}