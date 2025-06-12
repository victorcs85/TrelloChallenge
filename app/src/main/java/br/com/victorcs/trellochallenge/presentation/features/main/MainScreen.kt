package br.com.victorcs.trellochallenge.presentation.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import br.com.victorcs.trellochallenge.R
import br.com.victorcs.trellochallenge.presentation.navigation.AppNavigation

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Text(text = stringResource(R.string.boards_title_label)) },
        content = { innerPadding ->
            AppNavigation(innerPadding, navController)
        }
    )
}
