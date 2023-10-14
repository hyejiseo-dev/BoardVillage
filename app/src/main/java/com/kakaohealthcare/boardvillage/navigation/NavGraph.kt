package com.kakaohealthcare.boardvillage.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kakaohealthcare.boardvillage.ui.screens.BoardMainScreen
import com.kakaohealthcare.boardvillage.ui.screens.SplashScreen
import com.kakaohealthcare.boardvillage.ui.screens.HomeScreen
import com.kakaohealthcare.boardvillage.ui.screens.ProfileScreen
import com.kakaohealthcare.boardvillage.ui.viewmodel.ProfileViewModel
import com.kakaohealthcare.boardvillage.ui.screens.UserScreen
import com.kakaohealthcare.boardvillage.ui.viewmodel.HomeViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController, viewModel)
        }

        composable(route = "${Screen.Profile.route}/{token}") {
            val viewModel = hiltViewModel<ProfileViewModel>()
            val token = it.arguments?.getString("token")
            if (token != null) { // 26983823
                ProfileScreen(navController = navController, viewModel = viewModel, token = token)
            }
        }

        composable(route = Screen.User.route) {
            UserScreen(navController = navController)
        }

        composable(route = Screen.BoardMain.route) {
            BoardMainScreen(navController = navController)
        }
    }
}
