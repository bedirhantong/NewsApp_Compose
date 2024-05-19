package com.example.news_compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuScreen(val route: String, val icon: ImageVector, val title: String) {

    data object TopNews : BottomMenuScreen("top_news", icon = Icons.Outlined.Home, "Top News")
    data object Favorites : BottomMenuScreen("favorites", icon = Icons.Outlined.Favorite, "Favorites")
}