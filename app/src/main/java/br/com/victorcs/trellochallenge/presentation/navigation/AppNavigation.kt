package br.com.victorcs.trellochallenge.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.victorcs.trellochallenge.presentation.features.boards.BoardsScreen
import br.com.victorcs.trellochallenge.presentation.features.boards.BoardsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRouter.Boards.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        composable(ScreenRouter.Boards.route) {
            val viewModel: BoardsViewModel = koinViewModel()
            val state = viewModel.screenState.collectAsStateWithLifecycle().value

            BoardsScreen(state, viewModel::execute)
        }
    }
}