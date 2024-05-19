package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.news_compose.components.SearchBar
import com.example.news_compose.network.NewsManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopNews(
    navController: NavController,
    articles:List<TopNewsArticle>,
    query:MutableState<String>,
    newsManager: NewsManager
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
            Spacer(modifier = Modifier.height(70.dp))
            SearchBar(query = query, newsManager = newsManager)
            val searchedText = query.value
            val searchedNews = mutableListOf<TopNewsArticle>()
            if (searchedText != ""){
                searchedNews.addAll(newsManager.searchedNewsResponse.value.articles?:articles)
            }else{
                searchedNews.addAll(articles)
            }
            LazyColumn{
                items(searchedNews.size){index->
                    TopNewsItem(article =searchedNews[index],
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
fun TopNewsItem(
    article: TopNewsArticle
    , onNewsClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clickable { onNewsClick() },
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = article.title ?: "No Title",
                    maxLines = 2,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = article.description ?: "No Description",
                    maxLines = 2,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = article.source?.name ?: "Unknown Source",
                    color = Color.Black,
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            CoilImage(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(30.dp)
                    ),
                imageModel = article.urlToImage,
                contentScale = ContentScale.FillHeight,
                error = ImageBitmap.imageResource(R.drawable.placeholder_centered),
                placeHolder = ImageBitmap.imageResource(R.drawable.up)
            )
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