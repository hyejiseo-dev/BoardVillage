package com.kakaohealthcare.boardvillage.ui.screens

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakaohealthcare.boardvillage.R
import com.kakaohealthcare.boardvillage.navigation.Screen
import com.kakaohealthcare.boardvillage.ui.viewmodel.HomeContract
import com.kakaohealthcare.boardvillage.ui.viewmodel.HomeViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Text(
            text = "수다쟁이가 되어보자!",
            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 100.dp)
                .align(alignment = Alignment.TopCenter),
            fontWeight = FontWeight.Bold
        )

        LottieTalk(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .fillMaxWidth()
                .height(300.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            KaKaoLogin(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(30.dp)
                    .combinedClickable {
                        when (viewModel.viewState) {

                        }
                    },
                btnTxt = "카카오 로그인"
            )
        }


    }
}

@Preview
@Composable
fun HomePreviews() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<HomeViewModel>()
    HomeScreen(navController = navController, viewModel = viewModel)
}


@Composable
fun LottieTalk(modifier: Modifier) {
    val clipSpecs = LottieClipSpec.Progress(0.2f, 0.5f)
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.talking_person))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        clipSpec = clipSpecs,
    )
}


@Composable
fun KaKaoLogin(
    navController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier,
    btnTxt: String
) {
// 카카오계정으로 로그인 공통 callback 구성
// 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(ContentValues.TAG, "카카오계정으로 로그인 실패", error)
            viewModel.viewState.value.loginSuccess = true
            viewModel.viewState.value.loginToken = "test"
        } else if (token != null) {
            Log.i(ContentValues.TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            viewModel.viewState.value.loginSuccess = false
            viewModel.viewState.value.loginToken = token.accessToken
        }

        if (viewModel.viewState.value.loginSuccess) {
            navController.navigate("${Screen.Profile.route}/${viewModel.viewState.value.loginToken}")
        } else {
            Log.i(ContentValues.TAG,"실패다아");
        }
    }

    Button(
        onClick = { // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context = navController.context)) {
                UserApiClient.instance.loginWithKakaoTalk(navController.context) { token, error ->
                    if (error != null) {
                        Log.e(ContentValues.TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(navController.context, callback = callback)
                    } else if (token != null) {
                        Log.i(ContentValues.TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(navController.context, callback = callback)
            }


        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color.Yellow),
        shape = CutCornerShape(10),
    ) {
        Text(text = btnTxt, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }

}
