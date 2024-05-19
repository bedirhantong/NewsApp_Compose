package com.example.news_compose.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.news_compose.BottomMenuScreen
import com.example.news_compose.R

@Composable
fun BottomMenu(navController: NavController) {
    val menuItems = listOf(
        BottomMenuScreen.TopNews,
        BottomMenuScreen.Favorites,
    )
    BottomAppBar(
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menuItems.forEach { menuItem ->
            NavigationBarItem(
                label = {
                    Text(
                        text = menuItem.title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.sp
                        )
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == menuItem.route,
                onClick = {
                    navController.navigate(menuItem.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == menuItem.route) menuItem.icon else menuItem.icon,
                        contentDescription = menuItem.title,
                        tint = if (currentRoute == menuItem.route) Color.White else Color.LightGray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color(0xFF062840),
                    indicatorColor = Color(0xFF062840),
                    unselectedTextColor = Color.LightGray
                )
            )
        }
    }
}


//    BottomNavigation(contentColor = colorResource(id = R.color.white))
//    {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//        menuItems.forEach {
//            BottomNavigationItem(
//                label = { Text(text = it.title) },
//                alwaysShowLabel = true,
//                selectedContentColor = Color.White,
//                unselectedContentColor = Color.Gray,
//                selected = currentRoute == it.route,
//                onClick = {
//                    navController.navigate(it.route) {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                },
//                icon = {
//                    Icon(
//                        imageVector = it.icon,
//                        contentDescription = it.title
//                    )
//                },
//
//                )
//
//        }
//    }