package com.kakaohealthcare.boardvillage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kakaohealthcare.boardvillage.R
import com.kakaohealthcare.boardvillage.navigation.Screen
import com.kakaohealthcare.boardvillage.ui.component.MoveBtn


@Composable
fun UserScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.egg_icon),
                contentDescription = "profile",
                modifier = Modifier.padding(10.dp).fillMaxWidth().height(300.dp),
            )

            Text(
                text = "Welcome Annie!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            MoveBtn(
                navController = navController,
                screen = Screen.Profile,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(30.dp),
                btnTxt = "수다 떨러가기"
            )
        }


    }
}

@Preview
@Composable
fun UserPreviews() {
    val navController = rememberNavController()
    UserScreen(navController = navController)
}

