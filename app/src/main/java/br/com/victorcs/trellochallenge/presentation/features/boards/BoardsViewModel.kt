package br.com.victorcs.trellochallenge.presentation.features.boards

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import br.com.victorcs.trellochallenge.core.base.BaseViewModel
import br.com.victorcs.trellochallenge.core.constants.GENERIC_MESSAGE_ERROR
import br.com.victorcs.trellochallenge.core.providers.IDispatchersProvider
import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.domain.model.Response
import br.com.victorcs.trellochallenge.domain.usecases.IGetBoardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

private const val STOP_TIMER_LIMIT = 5000L

class BoardsViewModel(
    private val useCase: IGetBoardsUseCase,
    dispatchers: IDispatchersProvider
) : BaseViewModel(dispatchers) {

    private val _state = MutableStateFlow(BoardsScreenState())

    val screenState: StateFlow<BoardsScreenState> = _state
        .onStart {
            fetchBoards()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMER_LIMIT),
            initialValue = BoardsScreenState().copy(isLoading = true)
        )

    fun execute(intent: BoardsIntent) = when (intent) {
        is BoardsIntent.FetchBoards -> fetchBoards()
    }

    private fun fetchBoards() {
        launch(
            block = {
                _state.value = _state.value.copy(
                    isLoading = true
                )
                val boardsResponse = useCase.invoke()

                when (boardsResponse) {
                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            boards = boardsResponse.data,
                            errorMessage = null
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            errorMessage = boardsResponse.errorMessage
                        )
                    }
                }
            },

            errorBlock = { error ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = error.message ?: GENERIC_MESSAGE_ERROR
                )
                Unit
            },
        )
    }
}

@Stable
@Immutable
data class BoardsScreenState(
    val boards: List<BoardItem>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
