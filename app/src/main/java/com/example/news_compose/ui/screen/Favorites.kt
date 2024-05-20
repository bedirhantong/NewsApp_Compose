package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.news_compose.models.TopNewsArticle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Favorites(){
    Scaffold(
        topBar = {
            FavAppBar()
        }
    ) {
        Text(
            text = "Favorites",
            fontWeight = FontWeight.SemiBold

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavAppBar() {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Favorites",
                fontWeight = FontWeight.SemiBold, fontSize = 27.sp

            )
        },

        )
}

@Preview(showBackground = true)
@Composable
fun FavoritesPreview() {
    TopNewsItem(
        TopNewsArticle(
        author = "Bedirhan Tong",
        title = "Appcent Internship",
        description = "Appcent Testcase",
        publishedAt = "2024-05-18T12:22:32Z"
    )
    )
}