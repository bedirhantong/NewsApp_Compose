package com.example.news_compose.ui

sealed class Screens (val route : String){
    object TopNews : Screens("news_screen")
    object DetailScreen : Screens("Detail/{index}")
    object FavoriteScreen : Screens("favorite_screen")
    object WebViewScreen : Screens("webview_screen?url={url}")
}