package com.example.news_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.news_compose.graphs.RootNavigationGraph
import com.example.news_compose.ui.theme.News_ComposeTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            News_ComposeTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}

