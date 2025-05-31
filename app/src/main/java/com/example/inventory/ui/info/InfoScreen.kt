package com.example.inventory.ui.info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.inventory.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.viewinterop.AndroidView
@Composable
fun InfoScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val youtubeUrl = stringResource(R.string.youtube_link)
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text(stringResource(R.string.info)) },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.informacje))
            Spacer(modifier = Modifier.height(16.dp))
            AndroidView(
                factory = {
                    WebView(it).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled= true
                        loadUrl(youtubeUrl)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

        }
    }
}