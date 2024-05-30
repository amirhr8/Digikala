package ir.truelearn.digikala.ui.screens.splash

import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.truelearn.digikala.R
import ir.truelearn.digikala.data.remote.CheckConnection.isNetworkAvailable
import ir.truelearn.digikala.navigation.Screen
import ir.truelearn.digikala.ui.components.Loading3Dots
import ir.truelearn.digikala.ui.theme.splashBg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val isNetworkAvailable = remember {
        isNetworkAvailable(context)
    }

    Splash(isNetworkAvailable) {
        if (isNetworkAvailable(context)) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }else{
            Toast.makeText(
                context,
                context.getString(R.string.check_net),
                Toast.LENGTH_LONG
            ).show()
        }
    }


    LaunchedEffect(true) {
        delay(2500)
        if (isNetworkAvailable) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}


@Composable
fun Splash(isNetworkAvailable: Boolean, onRetryClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.splashBg)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.digi_logo),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                modifier = Modifier.height(30.dp),
                painter = painterResource(id = R.drawable.digi_txt_white),
                contentDescription = null
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (isNetworkAvailable) {
                Loading3Dots(false)
            } else {
                ReTry(onRetryClick = onRetryClick)
            }
        }
    }
}

@Composable
private fun ReTry(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onRetryClick)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.h6,
            text = stringResource(R.string.check_net),
            color = Color.White
        )
        Icon(
            Icons.Filled.Refresh,
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}