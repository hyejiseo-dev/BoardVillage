package com.kakaohealthcare.boardvillage.navigation


sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Profile : Screen("profile_screen")
    object User : Screen("user_screen")
    object BoardMain : Screen("board_main_screen")
}