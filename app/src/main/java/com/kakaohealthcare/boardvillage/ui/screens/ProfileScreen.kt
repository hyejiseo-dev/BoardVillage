package com.kakaohealthcare.boardvillage.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel, token: String) {

    Log.d("get token : ", token);
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

            var username = remember {
                mutableStateOf("")
            }
            var phoneNumber = remember {
                mutableStateOf("")
            }

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
                label = { Text(text = "NAME") },
                value = username.value,
                onValueChange = {
                    username.value = it
                    viewModel.setLdapID(username.value)
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
                value = phoneNumber.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    phoneNumber.value = it
                    viewModel.setPhoneNumber(phoneNumber.value)
                })

        }

        Button(
            onClick = {
                viewModel.saveProfile().run {
                    if(viewModel.viewState.value.saveProfile){
                        navController.navigate(Screen.BoardMain.route)
                        viewModel.setInitialState()
                    } else{
                        Log.i("tag","fail to move")
                    }
                }
            },
           modifier = Modifier
               .fillMaxWidth()
               .height(120.dp)
               .padding(30.dp)
               .align(alignment = Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(Color.Yellow),
            shape = CutCornerShape(10),
        ) {
            Text(text = "프로필 만들기", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

//        MoveBtn(
//            navController = navController, screen = Screen.Home,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(120.dp)
//                .padding(30.dp)
//                .align(alignment = Alignment.BottomCenter),
//            btnTxt = "프로필 만들기"
//        )

    }
}

@Preview
@Composable
fun ProfilePreviews() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<ProfileViewModel>()
    ProfileScreen(navController = navController, viewModel = viewModel, token = "")
}



