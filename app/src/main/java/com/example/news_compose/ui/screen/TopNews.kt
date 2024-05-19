package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.news_compose.MockData
import com.example.news_compose.NewsData
//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun TopNews(
//    navController: NavController,
//) {
//    var isSearchActive by rememberSaveable { mutableStateOf(false) }
//    var searchQuery by rememberSaveable { mutableStateOf("") }
//
//    Scaffold(
//        topBar = {
//            Column(verticalArrangement = Arrangement.spacedBy((0).dp)) {
//                AnimatedVisibility(
//                    visible = isSearchActive,
//                    enter = fadeIn() + slideInVertically(),
//                    exit = fadeOut() + slideOutVertically()
//                ) {
//                    TopAppBarSurface {
//                        EmbeddedSearchBar(
//                            onQueryChange = { searchQuery = it },
//                            isSearchActive = isSearchActive,
//                            onActiveChanged = { isSearchActive = it }
//                        )
//                    }
//                }
//                AnimatedVisibility(
//                    visible = !isSearchActive,
//                    enter = fadeIn() + slideInVertically(),
//                    exit = fadeOut() + slideOutVertically()
//                ) {
//                    TopAppBar1(
//                        onSearchIconClick = { isSearchActive = true }
//                    )
//                }
//            }
//        }
//    ) {
//        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Spacer(modifier = Modifier.height(50.dp))
//            LazyColumn {
//                items(MockData.topNewsList) { newsData ->
//                    TopNewsItem(
//                        newsData = newsData,
//                        onNewsClick = {
//                            navController.navigate("Detail/${newsData.id}")
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopAppBar1(
//    onSearchIconClick: () -> Unit
//) {
//    CenterAlignedTopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = Color.White,
//            titleContentColor = Color.Black
//        ),
//        title = {
//            Text(
//                text = "News",
//                fontWeight = FontWeight.SemiBold
//            )
//        },
//        actions = {
//            IconButton(onClick = onSearchIconClick) {
//                Icon(
//                    imageVector = Icons.Rounded.Search,
//                    contentDescription = "Search",
//                    tint = Color.Black
//                )
//            }
//        }
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EmbeddedSearchBar(
//    onQueryChange: (String) -> Unit,
//    isSearchActive: Boolean,
//    onActiveChanged: (Boolean) -> Unit,
//    modifier: Modifier = Modifier,
//    onSearch: ((String) -> Unit)? = null
//) {
//    var searchQuery by rememberSaveable { mutableStateOf("") }
//    val activeChanged: (Boolean) -> Unit = { active ->
//        searchQuery = ""
//        onQueryChange("")
//        onActiveChanged(active)
//    }
//
//    SearchBar(
//        query = searchQuery,
//        onQueryChange = { query ->
//            searchQuery = query
//            onQueryChange(query)
//        },
//        onSearch = onSearch ?: { activeChanged(false) },
//        active = isSearchActive,
//        onActiveChange = activeChanged,
//        modifier = if (isSearchActive) {
//            modifier
//                .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
//        } else {
//            modifier
//                .padding(start = 12.dp, top = 0.dp, end = 12.dp, bottom = 12.dp)
//                .fillMaxWidth()
//                .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
//        },
//        placeholder = { Text("Search", color = Color.Gray) },
//        leadingIcon = {
//            if (isSearchActive) {
//                IconButton(
//                    onClick = { activeChanged(false) }
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.ArrowBack,
//                        contentDescription = "",
//                        tint = Color.White
//                    )
//                }
//            } else {
//                Icon(
//                    imageVector = Icons.Rounded.Search,
//                    contentDescription = null,
//                    tint = Color.White
//                )
//            }
//        },
//        trailingIcon = if (isSearchActive && searchQuery.isNotEmpty()) {
//            {
//                IconButton(
//                    onClick = {
//                        searchQuery = ""
//                        onQueryChange("")
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Close,
//                        contentDescription = "",
//                        tint = Color.White
//                    )
//                }
//            }
//        } else {
//            null
//        },
//        colors = SearchBarDefaults.colors(
//            containerColor = Color(0xFF062840),
//            inputFieldColors = TextFieldDefaults.colors(
//                cursorColor = Color.White,
//                focusedTextColor = Color.White
//            )
//        ),
//        tonalElevation = 0.dp,
//        windowInsets = if (isSearchActive) {
//            SearchBarDefaults.windowInsets
//        } else {
//            WindowInsets(0.dp)
//        }
//    ) {
//        // Search suggestions or results
//    }
//}
//
//@Composable
//fun TopNewsItem(newsData: NewsData, onNewsClick: () -> Unit = {}) {
//    Box(
//        modifier = Modifier
//            .height(200.dp)
//            .padding(8.dp)
//            .clickable {
//                onNewsClick()
//            }
//    ) {
//        Image(
//            painter = painterResource(id = newsData.image),
//            contentDescription = "",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.fillMaxSize()
//        )
//        Column(
//            modifier = Modifier
//                .wrapContentHeight()
//                .padding(top = 16.dp, start = 16.dp),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = newsData.publishedAt.substring(0, 10),
//                color = Color.White,
//                fontWeight = FontWeight.SemiBold
//            )
//            Spacer(modifier = Modifier.height(100.dp))
//            Text(
//                text = newsData.title,
//                color = Color.White,
//                fontWeight = FontWeight.SemiBold
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TopNewsPreview() {
//    TopNewsItem(
//        NewsData(
//            2,
//            author = "Bedirhan Tong",
//            title = "Appcent Internship",
//            description = "Appcent Testcase",
//            publishedAt = "2024-05-18T12:22:32Z"
//        )
//    )
//}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopNews(
    navController: NavController,
//    navController: NavHostController = rememberNavController()

) {
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold (
        topBar = {
            Column (verticalArrangement = Arrangement.spacedBy((-1).dp)){
                TopAppBar1()
                TopAppBarSurface(scrollBehavior = scrollBehavior) {
                    EmbeddedSearchBar(
                        onQueryChange = {},
                        isSearchActive = isSearchActive,
                        onActiveChanged = { isSearchActive = it },
                    )
                }
            }
        },
//        topBar = {
//            TopAppBar1()
//        }
    ){
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "",fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(120.dp))
            LazyColumn{
                items(MockData.topNewsList){ newsData->
                    TopNewsItem(newsData = newsData,
                        onNewsClick = {
//                            navController.navigate(Graph.DETAILS)
                        navController.navigate("Detail/${newsData.id}")
                        }
                    )
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarSurface(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    content: @Composable () -> Unit,
) {
    val colorTransitionFraction = scrollBehavior?.state?.overlappedFraction ?: 0f
    val fraction = if (colorTransitionFraction > 0.01f) 1f else 0f
    Surface(
        modifier = modifier.fillMaxWidth(),
        content = content,
    )
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
@OptIn(ExperimentalMaterial3Api::class)
fun EmbeddedSearchBar(
    onQueryChange: (String) -> Unit,
    isSearchActive: Boolean,
    onActiveChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    onSearch: ((String) -> Unit)? = null,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val activeChanged: (Boolean) -> Unit = { active ->
        searchQuery = ""
        onQueryChange("")
        onActiveChanged(active)
    }
    SearchBar(
        query = searchQuery,
        onQueryChange = { query ->
            searchQuery = query
            onQueryChange(query)
        },
        onSearch = onSearch ?: { activeChanged(false) },
        active = isSearchActive,
        onActiveChange = activeChanged,
        modifier = if (isSearchActive) {
            modifier
                .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
        } else {
            modifier
                .padding(start = 12.dp, top = 0.dp, end = 12.dp, bottom = 12.dp)
                .fillMaxWidth()
                .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
        },
        placeholder = { Text("Search", color =  Color.White) },
        leadingIcon = {
            if (isSearchActive) {
                IconButton(
                    onClick = { activeChanged(false) },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = Color.White,
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },
        trailingIcon = if (isSearchActive && searchQuery.isNotEmpty()) {
            {
                IconButton(
                    onClick = {
                        searchQuery = ""
                        onQueryChange("")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "",
                        tint = Color.White,
                    )
                }
            }
        } else {
            null
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color(0xFF062840),
            inputFieldColors = TextFieldDefaults.colors(
                cursorColor = Color.White,
                focusedTextColor = Color.White
            )

        ),
        tonalElevation = 0.dp,
        windowInsets = if (isSearchActive) {
            SearchBarDefaults.windowInsets
        } else {
            WindowInsets(0.dp)
        }
    ) {
        // Search suggestions or results
    }
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