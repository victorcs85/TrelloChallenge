package br.com.victorcs.trellochallenge.presentation.navigation

private const val BOARDS_SCREEN = "boards"

sealed class ScreenRouter(val route: String) {
    object Boards : ScreenRouter(BOARDS_SCREEN)
}
