package ir.truelearn.digikala.ui.screens.product_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.truelearn.digikala.data.model.product_detail.ProductColor
import ir.truelearn.digikala.ui.theme.darkText
import ir.truelearn.digikala.ui.theme.spacing

@Composable
fun ProductSelectColorSection(
    colors: List<ProductColor>
) {

    Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {

        Text(
            text = "رنگ : انتخاب نشده",
            color = MaterialTheme.colors.darkText,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )


        LazyRow() {
            items(colors) { productColor ->
                ColorChipItem(productColor)
            }
        }
    }

}