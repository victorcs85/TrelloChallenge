package br.com.victorcs.trellochallenge.shared.test

import br.com.victorcs.trellochallenge.data.dto.BoardItemDto
import br.com.victorcs.trellochallenge.data.source.remote.exceptions.WithoutNetworkException
import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.domain.model.ErrorType
import br.com.victorcs.trellochallenge.domain.model.Response
import br.com.victorcs.trellochallenge.presentation.features.boards.BoardsScreenState
import java.util.InputMismatchException

object MockTests {

    const val GENERIC_ERROR = "Ocorreu um erro ao buscar os dados!"

    const val EMPTY_DATA_ERROR = "Não foram localizados dados para amostragem."

    const val NETWORK_ERROR = "Sem conexão. Verifique e tente novamente."

    const val POSITION_ONE = "1"

    const val BOARD_NAME_ONE = "Board 1"

    val boardItemsMock = listOf(
        BoardItem(id = POSITION_ONE, name = "Board", desc = "Board de teste 1", position = 1, closed = false),
        BoardItem(id = "2", name = "Board", desc = "Board de teste 2", position = 2, closed = true),
        BoardItem(id = "3", name = "Board", desc = "Board de teste 3", position = 3, closed = false),
    )

    val boardItemsDtoMock = listOf(
        BoardItemDto(id = POSITION_ONE, name = "Board", desc = "Board de teste 1", closed = false),
        BoardItemDto(id = "2", name = "Board", desc = "Board de teste 2", closed = true),
        BoardItemDto(id = "3", name = "Board", desc = "Board de teste 3", closed = false),
    )

    val boardItemsResponseMock = Response.Success(data = listOf(
        BoardItem(id = POSITION_ONE, name = "Board", desc = "Board de teste 1", position = 1, closed = false),
        BoardItem(id = "2", name = "Board", desc = "Board de teste 2", position = 2, closed = true),
        BoardItem(id = "3", name = "Board", desc = "Board de teste 3", position = 3, closed = false),
    ))

    val emptyResponseMock = Response.Success(data = emptyList<BoardItem>())

    val genericErrorMock = InputMismatchException(GENERIC_ERROR)

    val errorNetworkMock = WithoutNetworkException()

    val genericResponseErrorMock = Response.Error(
        errorMessage = GENERIC_ERROR,
        errorType = ErrorType.GENERIC_ERROR
    )

    val boardScreenStateMock = BoardsScreenState(
        isLoading = false,
        boards = boardItemsMock,
        errorMessage = null
    )
}