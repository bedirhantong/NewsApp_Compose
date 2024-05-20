package com.example.news_compose.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.news_compose.BottomMenuScreen
import com.example.news_compose.components.BottomMenu
import com.example.news_compose.models.TopNewsArticle
import com.example.news_compose.network.Api
import com.example.news_compose.network.NewsManager
import com.example.news_compose.ui.screen.DetailScreen
import com.example.news_compose.ui.screen.Favorites
import com.example.news_compose.ui.screen.TopNews


@Composable
fun NewsApp(mainViewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(
        navController = navController,
        scrollState,
        mainViewModel = mainViewModel
    )
}

@Composable
fun MainScreen(navController: NavHostController,scrollState: ScrollState,mainViewModel: MainViewModel) {
    Scaffold(bottomBar ={
        BottomMenu(navController = navController)
    }) {
        Navigation(navController =navController ,
            scrollState = scrollState,
            paddingValues = it ,
            viewModel = mainViewModel)
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    paddingValues: PaddingValues,
    newsManager: NewsManager = NewsManager(Api.retrofitService),
    viewModel: MainViewModel

) {
    val articles = mutableListOf(TopNewsArticle())
    val topArticles = viewModel.newsResponse.collectAsState().value.articles
    articles.addAll(topArticles ?: listOf())

    Log.d("news","$articles")
    articles.let {
        NavHost(
            navController = navController,
            startDestination = BottomMenuScreen.TopNews.route,
            Modifier.padding(paddingValues)
        ) {
            val queryState = mutableStateOf(viewModel.query.value)
            bottomNavigation(navController = navController,articles, query = queryState,viewModel)
            composable("Detail/{index}",
                arguments = listOf(
                    navArgument("index") { type = NavType.IntType }
                )) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let {
                    if (queryState.value != ""){
                        articles.clear()
                        articles.addAll(viewModel.searchedNewsResponse.value.articles?: listOf())
                    }else{
                        articles.clear()
                        articles.addAll(viewModel.newsResponse.value.articles ?: listOf())
                    }
                    val article = articles[index]
                    DetailScreen(article, scrollState, navController)
                }
            }


        }
    }


}

fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    articles:List<TopNewsArticle>,
    query : MutableState<String>,
    viewModel: MainViewModel

) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController,articles, query, viewModel = viewModel)
    }
    composable(BottomMenuScreen.Favorites.route) {
        Favorites()
    }
}