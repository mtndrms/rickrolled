package com.example.rickrolled.ui.screen.splash

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.rickrolled.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val colorTextStart = MaterialTheme.colors.primary
    val colorTextEnd = MaterialTheme.colors.secondary
    val textColor = remember { Animatable(colorTextStart) }

    LaunchedEffect(key1 = true) {
        textColor.animateTo(Color.White, tween(1000))
        delay(1000)
        navController.popBackStack()
        navController.navigate("character_list")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(colorTextEnd, colorTextStart))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GifImage()
        Text(
            text = stringResource(id = R.string.app_name).lowercase(),
            fontSize = 30.sp,
            color = textColor.value
        )
    }
}

@Composable
fun GifImage() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).components {
        if (SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.splash_2).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ), contentDescription = "splash screen logo"
    )
}