package com.kakaohealthcare.boardvillage.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kakaohealthcare.boardvillage.R
import com.kakaohealthcare.boardvillage.navigation.Screen


@Composable
fun SplashScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val composeChick by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.intro_chick))
        val bottomProgress by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.intro_progress))
        val chickAnimationState =
            animateLottieCompositionAsState(composition = composeChick)
        val progressAnimationState =
            animateLottieCompositionAsState(composition = composeChick)

        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                composition = composeChick,
                progress = { chickAnimationState.progress }
            )

            Text(
                text = "Loading...",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

        }

        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter),
            composition = bottomProgress,
            progress = { progressAnimationState.progress }
        )

        if (chickAnimationState.isAtEnd && chickAnimationState.isPlaying || progressAnimationState.isAtEnd && progressAnimationState.isPlaying) {
            navController.navigate(Screen.Home.route)
        }
    }
}

@Preview
@Composable
fun SplashPreviews() {
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}
