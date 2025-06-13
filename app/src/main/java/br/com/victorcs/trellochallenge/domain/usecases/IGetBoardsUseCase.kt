package br.com.victorcs.trellochallenge.domain.usecases

import br.com.victorcs.trellochallenge.domain.repository.BoardsResponse

interface IGetBoardsUseCase {
    suspend operator fun invoke(): BoardsResponse
}