package br.com.victorcs.trellochallenge.presentation.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.victorcs.trellochallenge.presentation.navigation.AppNavigation
import br.com.victorcs.trellochallenge.presentation.views.TopBar

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        content = { innerPadding ->
            AppNavigation(innerPadding, navController)
        }
    )
}
