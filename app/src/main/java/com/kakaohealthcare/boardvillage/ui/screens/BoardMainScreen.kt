package com.kakaohealthcare.boardvillage.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kakaohealthcare.boardvillage.R
import com.kakaohealthcare.boardvillage.domain.model.Board
import com.kakaohealthcare.boardvillage.domain.model.DataProvider


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardMainScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(),
        floatingActionButton = {
            AddTweetButton()
        }
    ) {
        EmptyContent()
      //  RecyclerViewContent()
    }
}

@Composable
fun AddTweetButton() {
    FloatingActionButton(onClick = {
        // 이동하기
    }) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "write",
            tint = Color.White,

        )
    }
}

@Composable
fun EmptyContent(){



}

@Composable
fun RecyclerViewContent() {
    val boards = remember { DataProvider.boardList }
    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp), modifier = Modifier.fillMaxSize()) {
        items(
            items = boards,
            itemContent = { BoardListItem(it) }
        )
    }
}

@Composable
fun BoardListItem(board: Board) {
    Row {
        BoardImage(board = board)
        Column {
            Text(text = board.title, fontSize = 20.sp, modifier = Modifier.padding(3.dp))
            Text(text = board.content, fontSize = 18.sp)
        }
    }
}

@Composable
fun BoardImage(board: Board) {
    Image(
        painter = painterResource(id = R.drawable.egg_icon),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(CornerSize(16.dp)))
    )
}

@Preview
@Composable
fun BoardMainPreviews() {
    val navController = rememberNavController()
    BoardMainScreen(navController = navController)
}



