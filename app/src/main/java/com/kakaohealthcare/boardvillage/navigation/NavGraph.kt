package com.kakaohealthcare.boardvillage.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kakaohealthcare.boardvillage.ui.screens.BoardMainScreen
import com.kakaohealthcare.boardvillage.ui.screens.SplashScreen
import com.kakaohealthcare.boardvillage.ui.screens.ProfileScreen
import com.kakaohealthcare.boardvillage.ui.viewmodel.ProfileViewModel
import com.kakaohealthcare.boardvillage.ui.screens.UserScreen
import com.kakaohealthcare.boardvillage.ui.viewmodel.LoginViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            SplashScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = Screen.Profile.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(navController, viewModel = viewModel)
        }

        composable(route = Screen.User.route) {
            UserScreen(navController = navController)
        }

        composable(route = Screen.BoardMain.route) {
            BoardMainScreen(navController = navController)
        }
    }
}
