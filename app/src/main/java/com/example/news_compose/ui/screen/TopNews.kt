package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.news_compose.MockData
import com.example.news_compose.NewsData
import com.example.news_compose.R
import com.example.news_compose.graphs.Graph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopNews(
//    navController: NavController
    navController: NavHostController = rememberNavController()
) {
    Scaffold (
        topBar = {
            TopAppBar1()
        }
    ){
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Top News",fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn{
                items(MockData.topNewsList){ newsData->
                    TopNewsItem(newsData = newsData,
                        onNewsClick = {
                            navController.navigate(Graph.DETAILS)
//                        navController.navigate("Detail/${newsData.id}")
                        }
                    )
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar1() {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        title = {
            Text(
                text = "News",
                fontWeight = FontWeight.SemiBold

            )
        },

    )
}


@Composable
fun TopNewsItem(newsData: NewsData,onNewsClick: () -> Unit = {},) {
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }) {
        Image(painter = painterResource(id = newsData.image), contentDescription ="",
            contentScale =  ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
            )
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = newsData.publishedAt.substring(0,10),color = Color.White,fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = newsData.title,color = Color.White ,fontWeight = FontWeight.SemiBold)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNewsItem(  NewsData(
        2,
        author = "Bedirhan Tong",
        title = "Appcent Internship",
        description = "Appcent Testcase",
        publishedAt = "2024-05-18T12:22:32Z"
    ))
}