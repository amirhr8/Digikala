package ir.truelearn.digikala.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.truelearn.digikala.ui.theme.roundedShape
import ir.truelearn.digikala.ui.theme.spacing
import ir.truelearn.digikala.R
import ir.truelearn.digikala.ui.components.IconWithRotate
import ir.truelearn.digikala.ui.theme.DigikalaLightRed
import ir.truelearn.digikala.ui.theme.darkText

@Composable
fun AmazingShowMoreItem() {
    Card(
        modifier = Modifier
            .size(180.dp, 375.dp)
            .padding(
                end = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.semiSmall,
                top = MaterialTheme.spacing.semiLarge
            ),
        shape = MaterialTheme.roundedShape.small,
        contentColor = Color.White
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {


            IconWithRotate(
                painterResource(id = R.drawable.show_more),
                MaterialTheme.colors.DigikalaLightRed
            )


            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )


        }


    }
}