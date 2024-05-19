package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.news_compose.R
import com.example.news_compose.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(

    article: TopNewsArticle, scrollState: ScrollState,navController: NavController
) {
    Scaffold(topBar = {
        TopAppBar(
            onBackPressed = {navController.popBackStack()}
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)
            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.Inside,
                error = ImageBitmap.imageResource(R.drawable.placeholder_centered),
                placeHolder = ImageBitmap.imageResource(R.drawable.placeholder_centered)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = article.author?:"Not avaliable")
                InfoWithIcon(icon = Icons.Default.DateRange, info = article.publishedAt!!)
            }
            Text(text = article.title?:"Not avaliable", fontWeight = FontWeight.Bold)
            Text(text = article.description?:"Not avaliable", modifier = Modifier.padding(top = 16.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(onBackPressed: () -> Unit = {}) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        title = {
            Text(
                text = "",
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { /* Web sayfasını aç */ }) {
                Icon(imageVector = Icons.Outlined.Share, contentDescription = "Share")
            }
            IconButton(onClick = { /* Web sayfasını aç */ }) {
                Icon(painter = painterResource(R.drawable.cccc), contentDescription = "Web",
                    Modifier
                        .height(28.dp)
                        .width(28.dp))
            }
        }
    )
}
@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            icon,
            contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(
                id = R.color.purple_500
            )
        )
        Text(text = info.substring(0,10))
    }
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        TopNewsArticle(
            author = "Bedirhan Tong",
            title = "Appcent Internship",
            description = "Appcent Testcase",
            publishedAt = "2024-05-18T12:22:32Z"
        ),
        rememberScrollState(),
        rememberNavController()
    )
}