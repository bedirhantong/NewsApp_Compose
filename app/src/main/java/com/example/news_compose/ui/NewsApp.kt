package com.example.news_compose.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.news_compose.network.NewsManager
import com.example.news_compose.ui.screen.DetailScreen
import com.example.news_compose.ui.screen.Favorites
import com.example.news_compose.ui.screen.TopNews


@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController = navController,scrollState)
}

@Composable
fun MainScreen(navController: NavHostController,scrollState: ScrollState) {
    Scaffold(bottomBar ={
        BottomMenu(navController = navController)
    }) {
        Navigation(navController =navController , scrollState = scrollState, paddingValues = it )
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    paddingValues: PaddingValues,
    newsManager: NewsManager = NewsManager(),

) {
    val articles = mutableListOf(TopNewsArticle())
    articles.addAll(newsManager.newsResponse.value.articles ?: listOf(TopNewsArticle()))

    Log.d("news","$articles")
    articles.let {
        NavHost(
            navController = navController,
            startDestination = BottomMenuScreen.TopNews.route,
            Modifier.padding(paddingValues)
        ) {
            bottomNavigation(navController = navController,articles,newsManager)
            composable("Detail/{index}",
                arguments = listOf(
                    navArgument("index") { type = NavType.IntType }
                )) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let {
                    if (newsManager.query.value.isNotEmpty()){
                        articles.clear()
                        articles.addAll(newsManager.searchedNewsResponse.value.articles?: listOf())
                    }else{
                        articles.clear()
                        articles.addAll(newsManager.newsResponse.value.articles ?: listOf())
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
    newsManager: NewsManager

) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController,articles, newsManager.query,newsManager)
    }
    composable(BottomMenuScreen.Favorites.route) {
        Favorites()
    }
}