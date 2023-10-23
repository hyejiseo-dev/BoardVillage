package com.kakaohealthcare.boardvillage.ui.component

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kakaohealthcare.boardvillage.navigation.Screen


@Composable
fun MoveBtn(navController: NavHostController, screen: Screen, modifier: Modifier, btnTxt: String) {
    Button(
        onClick = { navController.navigate(screen.route) },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color.Yellow),
        shape = CutCornerShape(10),
    ) {
        Text(text = btnTxt, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
