package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.news_compose.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage
import com.example.news_compose.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopNews(
    navController: NavController,articles:List<TopNewsArticle>
) {
    Scaffold (
        topBar = {
            TopAppBar1()
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "",fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(50.dp))
            LazyColumn{
                items(articles.size){index->
                    TopNewsItem(article =articles[index],
                        onNewsClick = {  navController.navigate("Detail/$index")}
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
fun TopNewsItem(article: TopNewsArticle, onNewsClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onNewsClick()
            }
    ) {
        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(R.drawable.placeholder_home),
            placeHolder = ImageBitmap.imageResource(R.drawable.placeholder_home)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xAA062840))
                    .padding(8.dp)
            ) {
                Text(
                    text = article.title?: "No Title",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xAA062840))
                    .padding(8.dp)
            ) {
                Text(
                    text = article.description?: "No Description",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNewsItem(  TopNewsArticle(
        author = "Bedirhan Tong",
        title = "Appcent Internship",
        description = "Appcent Testcase",
        publishedAt = "2024-05-18T12:22:32Z"
    ))
}