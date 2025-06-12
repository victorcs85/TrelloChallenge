package br.com.victorcs.trellochallenge.presentation.features.boards

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BoardsScreen(
    navController: NavController,
    state: BoardsScreenState,
    execute: (BoardsIntent) -> Unit,
) {

    val navController = rememberNavController()



}