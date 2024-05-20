package com.example.news_compose.ui.screen

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.news_compose.models.TopNewsArticle

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    article : TopNewsArticle,
    navController: NavController? = null
){
    Scaffold {
        Box (
            modifier = Modifier.padding(it)
        ){
            AndroidView(
                factory = {context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()

                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)
                }
            },
               update = {
                   article.url?.let { it1 -> it.loadUrl(it1) }

               }
            )
        }
    }
}