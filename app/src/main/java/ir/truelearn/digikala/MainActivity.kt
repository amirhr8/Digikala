package ir.truelearn.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.truelearn.digikala.navigation.BottomNavigationBar
import ir.truelearn.digikala.navigation.SetupNavGraph
import ir.truelearn.digikala.ui.components.AppConfig
import ir.truelearn.digikala.ui.components.ChangeStatusBarColor
import ir.truelearn.digikala.ui.theme.DigikalaTheme
import ir.truelearn.digikala.util.AES
import ir.truelearn.digikala.util.Constants.ENGLISH_LANG
import ir.truelearn.digikala.util.Constants.KEY
import ir.truelearn.digikala.util.Constants.PERSIAN_LANG
import ir.truelearn.digikala.util.Constants.USER_LANGUAGE
import ir.truelearn.digikala.util.LocaleUtils

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()
                ChangeStatusBarColor(navController = navController)


                AppConfig()

                Log.e("3636", USER_LANGUAGE)

                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

                val direction = if (USER_LANGUAGE == ENGLISH_LANG) {
                    androidx.compose.ui.unit.LayoutDirection.Ltr
                } else {
                    androidx.compose.ui.unit.LayoutDirection.Rtl
                }
                CompositionLocalProvider(LocalLayoutDirection provides direction) {

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                })
                        }
                    ) {
                        SetupNavGraph(navController = navController)
                    }

                }


            }
        }
    }
}
