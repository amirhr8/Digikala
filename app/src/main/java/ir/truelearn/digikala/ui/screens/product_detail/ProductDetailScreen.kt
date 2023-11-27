package ir.truelearn.digikala.ui.screens.product_detail

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.icu.text.CaseMap.Title
import android.os.Build
import android.text.Layout
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.endAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.fromComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.style.LocalChartStyle
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.axis.horizontal.createHorizontalAxis
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.axis.vertical.createVerticalAxis
import com.patrykandpatrick.vico.core.chart.composed.plus
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes.pillShape
import com.patrykandpatrick.vico.core.component.shape.Shapes.rectShape
import com.patrykandpatrick.vico.core.component.shape.cornered.Corner
import com.patrykandpatrick.vico.core.component.shape.cornered.CorneredShape
import com.patrykandpatrick.vico.core.component.shape.cornered.CutCornerTreatment
import com.patrykandpatrick.vico.core.component.shape.cornered.RoundedCornerTreatment
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.composed.plus
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.scroll.AutoScrollCondition
import com.patrykandpatrick.vico.core.scroll.InitialScroll
import ir.truelearn.digikala.R
import ir.truelearn.digikala.data.model.product_detail.Comment
import ir.truelearn.digikala.data.model.product_detail.ProductColor
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import ir.truelearn.digikala.data.model.product_detail.SliderImage
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.ui.components.OurLoading
import ir.truelearn.digikala.ui.theme.darkText
import ir.truelearn.digikala.ui.theme.spacing
import ir.truelearn.digikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {

    var productDetailList by remember {
        mutableStateOf<ProductDetail>(ProductDetail())
    }

    var imageSlider by remember {
        mutableStateOf<List<SliderImage>>(emptyList())
    }
    var productColors by remember {
        mutableStateOf<List<ProductColor>>(emptyList())
    }
    var productComments by remember {
        mutableStateOf<List<Comment>>(emptyList())
    }
    var categoryId by remember { mutableStateOf("") }

    var description by rememberSaveable { mutableStateOf("") }

    var technicalFeatures by remember { mutableStateOf("") }

    var commentCount by remember { mutableStateOf(0) }

    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.getProductById(productId)
        viewModel.productDetail.collectLatest { productDetail ->
            when (productDetail) {
                is NetworkResult.Success -> {
                    productDetailList = productDetail.data!!
                    imageSlider = productDetail.data.imageSlider ?: emptyList()
                    productColors = productDetail.data.colors ?: emptyList()
                    productComments = productDetail.data.comments ?: emptyList()
                    categoryId = productDetail.data.categoryId ?: ""
                    description = productDetail.data.description ?: ""
                    commentCount = productDetail.data.commentCount ?: 0
                    technicalFeatures = productDetail.data.technicalFeatures.toString()
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${productDetail.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }

    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(config.screenHeightDp.dp, true)
    } else {
        Scaffold(
            bottomBar = {
                ProductDetailBottomBar(productDetailList, navController)
            },
            topBar = {
                ProductTopAppBar(navController)
            }
        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {

                val chartEntryModel = entryModelOf(4f, 12f, 8f, 16f)

                item {
                    DefaultLineChart()
                    DefaultColumnChart()
                    ColumnChartWithNegativeValues()
                    LineChartCard()
                    LineChartDark()
                    ComposedLineChart()
                }

                item { ProductTopSliderSection(imageSlider) }
                item { ProductDetailHeaderSection(productDetailList) }
                item { ProductSelectColorSection(productColors) }
                item { SellerInfoSection() }
                item { SimilarProductSection(categoryId) }
                item { ProductDescriptionSection(navController, description, technicalFeatures) }
                item {
                    ProductCommentsSection(
                        navController,
                        productId,
                        productComments,
                        commentCount.toString()
                    )
                }
                item { ProductSetCommentsSection(navController, productDetailList) }
            }
        }
    }


}


@Composable
fun ColumnChartWithNegativeValues() {
    ChartCard(
        title = "نمودار ستونی منفی",
        subTitle = "مثالی برای استفاده از نمودار ستونی با مقادیر منفی"
    ) {
        Chart(
            chart = columnChart(
                dataLabel = textComponent(),
            ),
            model = entryModelOf(2f, -1f, 4f, -2f, 1f, 5f, -3f),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
        )
    }
}

@Composable
 fun DefaultLineChart(
    model: ChartEntryModel = entryModelOf(2f, 1f, 4f, 8f, 1f, 5f, 7f),
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
) {
    ChartCard(
        title = "نمودار خطی",
        subTitle = "مثالی برای استفاده از نمودار خطی"
    ) {
        Chart(
            chart = lineChart(),
            model = model,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll
            )
        )
    }
}


@Composable
fun DefaultColumnChart(
    model: ChartEntryModel = entryModelOf(2f, 1f, 4f, 8f, 1f, 5f, 7f),
    oldModel: ChartEntryModel? = null,
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
    autoScrollCondition: AutoScrollCondition<ChartEntryModel> = AutoScrollCondition.Never,
) {
    ChartCard(
        title = "نمودار ستونی",
        subTitle = "مثالی برای استفاده از نمودار ستونی"
    ) {
        Chart(
            chart = columnChart(),
            model = model,
            oldModel = oldModel,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll,
                autoScrollCondition = autoScrollCondition,
            ),
        )
    }


}

@Composable
fun LineChartCard() {
    val colors = MaterialTheme.colors

    ChartCard(
        title = "نمودار خطی زیبا",
        subTitle = "مثالی برای استفاده از نمودار خطی خاص"
    ) {
        Chart(
            modifier = Modifier.height(100.dp),
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        point = null,
                        lineColor = colors.primary,
                        lineBackgroundShader = DynamicShaders.fromComponent(
                            componentSize = 4.dp,
                            component = shapeComponent(
                                shape = pillShape,
                                color = colors.primary
                            ).apply {
                                setMargins(0.5.dp.value)
                            },
                        ),
                    ),
                ),
                axisValuesOverrider = AxisValuesOverrider.fixed(
                    minX = 0f,
                    maxY = 3f,
                ),
            ),
            model = entryModelOf(-1 to 0, 0 to 0, 1 to 1, 2 to 2, 3 to 0, 4 to 2, 5 to 1),
            startAxis = createVerticalAxis {
                label = textComponent(
                    color = colors.onSurface,
                    textSize = 10.sp,
                    background = shapeComponent(shape = rectShape, color = Color.LightGray),
                    padding = dimensionsOf(horizontal = 4.dp, vertical = 2.dp),
                )
                axis = null
                tick = null
                guideline = LineComponent(
                    color = Color.LightGray.toArgb(),
                    thicknessDp = 1.dp.value,
                    shape = DashedShape(
                        shape = pillShape,
                        dashLengthDp = 2.dp.value,
                        gapLengthDp = 4.dp.value,
                    ),
                )
                horizontalLabelPosition = VerticalAxis.HorizontalLabelPosition.Inside
            },
            bottomAxis = createHorizontalAxis {
                label = null
                tick = null
                guideline = null
                axis = lineComponent(color = Color.LightGray, thickness = 1.dp)
            },
        )
    }
}

@Composable
private fun ChartCard(
    title: String,
    subTitle: String,
    chart: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            chart()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.h5,
            )
        }
    }
}


private val model1 = entryModelOf(0, 2, 4, 0, 2)
private val model2 = entryModelOf(1, 3, 4, 1, 3)
private val model3 = entryModelOf(entriesOf(3, 2, 2, 3, 1), entriesOf(1, 3, 1, 2, 3))

@Composable
fun LineChartDark() {
    ChartCard(
        title = "نمودار خطی رنگی دوتایی",
        subTitle = "مثالی برای استفاده از نمودار خطی رنگی دوتایی بدون بک گراند"
    ) {
        val yellow = Color(0xFFFFAA4A)
        val pink = Color(0xFFFF4AAA)

        Chart(
            modifier = Modifier.padding(8.dp),
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = yellow,
                        lineBackgroundShader = verticalGradient(
                            arrayOf(yellow.copy(0.5f), yellow.copy(alpha = 0f)),
                        ),
                    ),
                    lineSpec(
                        lineColor = pink,
                        lineBackgroundShader = verticalGradient(
                            arrayOf(pink.copy(0.5f), pink.copy(alpha = 0f)),
                        ),
                    ),
                ),
                axisValuesOverrider = AxisValuesOverrider.fixed(maxY = 4f),
            ),
            model = model3,
        )
    }
}

@Composable
fun ComposedLineChart() {
    ChartCard(
        title = "نمودار خطی رنگی دوتایی",
        subTitle = "مثالی برای استفاده از خطی رنگی دوتایی"
    ) {
        Chart(
            chart = lineChart() + lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = Color.Blue,
                        lineBackgroundShader = verticalGradient(
                            colors = arrayOf(
                                Color.Blue.copy(alpha = 0.4f),
                                Color.Blue.copy(alpha = 0f),
                            ),
                        ),
                    ),
                ),
            ),
            model = model1 + model2,
            startAxis = rememberStartAxis(),
        )
    }
}
