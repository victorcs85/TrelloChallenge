package br.com.victorcs.trellochallenge.presentation.features.boards

sealed class BoardsIntent {
    object FetchBoards : BoardsIntent()
}
