package com.kakaohealthcare.boardvillage.ui.screens

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kakaohealthcare.boardvillage.R
import com.kakaohealthcare.boardvillage.navigation.Screen
import com.kakaohealthcare.boardvillage.ui.viewmodel.ProfileContract
import com.kakaohealthcare.boardvillage.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel) {

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is ProfileContract.Effect.ShowSnackBar -> {
                    snackBarHostState.showSnackbar("프로필 등록에 실패 했습니다!")
                }

                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(top = 100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.egg_profile),
                    contentDescription = "profile",
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(300.dp),
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    label = { Text(text = "LDAP ID") },
                    value = viewModel.viewState.value.ldapId,
                    onValueChange = { newValue ->
                        viewModel.inputLdapId(newValue)
                    })

                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    label = { Text(text = "PHONE NUMBER") },
                    value = viewModel.viewState.value.phoneNumber,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { newValue ->
                        viewModel.inputPhoneNumber(newValue)
                    })

            }

            Button(
                onClick = {
                    viewModel.saveProfile()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(30.dp)
                    .align(alignment = Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(Color.Yellow),
                shape = CutCornerShape(10),
            ) {
                Text(
                    text = "프로필 만들기",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (viewModel.viewState.value.saveProfile) {
                navController.navigate(Screen.BoardMain.route)
            }

        }
    }
}

@Preview
@Composable
fun ProfilePreviews() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<ProfileViewModel>()
    ProfileScreen(navController = navController, viewModel = viewModel)
}



