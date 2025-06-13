package br.com.victorcs.trellochallenge.domain.usecases

import br.com.victorcs.trellochallenge.domain.model.Response
import br.com.victorcs.trellochallenge.domain.repository.BoardsResponse
import br.com.victorcs.trellochallenge.domain.repository.IBoardsRepository

class GetBoardsUseCaseImpl(private val repository: IBoardsRepository): IGetBoardsUseCase {

    override suspend fun invoke(): BoardsResponse {
        return when (val result = repository.getBoards()) {
            is Response.Success -> {
                val boardsWithPosition = result.data.mapIndexed { index, board ->
                    board.copy(position = index + 1)
                }
                Response.Success(boardsWithPosition)
            }

            is Response.Error -> result
        }
    }
}