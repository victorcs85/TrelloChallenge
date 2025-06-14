package br.com.victorcs.trellochallenge.presentation.features.boards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.victorcs.trellochallenge.R
import br.com.victorcs.trellochallenge.core.extensions.orFalse
import br.com.victorcs.trellochallenge.presentation.views.EmptyInfoListView
import br.com.victorcs.trellochallenge.presentation.views.LoadingView
import br.com.victorcs.trellochallenge.presentation.views.ShowErrorMessage

@Composable
fun BoardsScreen(
    state: BoardsScreenState,
    execute: (BoardsIntent) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        BoardsScreenContent(state, execute)
    }

}

@Composable
fun BoardsScreenContent(
    state: BoardsScreenState,
    execute: (BoardsIntent) -> Unit
) {
    val listState = rememberLazyListState()

    when {
        state.errorMessage != null -> ShowErrorMessage(
            errorMessage = state.errorMessage,
            buttonLabel = stringResource(R.string.reload),
            buttonAction = {
                execute(BoardsIntent.FetchBoards)
            },
            modifier = null,
        )

        state.isLoading -> LoadingView()
        state.boards?.isEmpty().orFalse() || state.boards == null -> EmptyInfoListView(
            buttonLabel = stringResource(R.string.reload),
            buttonAction = {
                execute(BoardsIntent.FetchBoards)
            },
            modifier = null,
        )

        else -> BoardList(
            boards = state.boards,
            listState = listState
        )
    }
}
