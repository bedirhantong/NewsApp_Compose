package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    article: TopNewsArticle, scrollState: ScrollState, navController: NavController
) {
    Scaffold(topBar = {
        TopAppBar(
            onBackPressed = { navController.popBackStack() },
            navController = navController,
            article = article
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.FillWidth,
                error = ImageBitmap.imageResource(R.drawable.placeholder_centered),
                placeHolder = ImageBitmap.imageResource(R.drawable.placeholder_centered)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        text = article.author ?: "Not available",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = article.publishedAt?.substring(0, 10) ?: "Unknown Source",
                )
            }

            Text(text = article.title ?: "Not available", fontWeight = FontWeight.Bold)
            Text(text = article.content ?: "Not available", modifier = Modifier.padding(top = 16.dp))
        }
    }
}

fun shareDetailedArticle(context: Context, article: TopNewsArticle) {


    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, "${article.url ?: article.title}")
        putExtra(Intent.EXTRA_TITLE, article.title ?: "")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)

    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(onBackPressed: () -> Unit = {}, navController: NavController, article: TopNewsArticle) {
    val context = LocalContext.current

    CenterAlignedTopAppBar(
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
            IconButton(onClick = {
                // TODO: Handling favorite article with ROOM
            }) {
                Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { shareDetailedArticle(context, article) }) {
                Icon(imageVector = Icons.Outlined.Share, contentDescription = "Share")
            }
            IconButton(onClick = {
                navController.navigate("webview_screen?url=${article.url}")
            }) {
                Icon(
                    painter = painterResource(R.drawable.cccc),
                    contentDescription = "Web",
                    Modifier
                        .height(28.dp)
                        .width(28.dp)
                )
            }
        }
    )
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
