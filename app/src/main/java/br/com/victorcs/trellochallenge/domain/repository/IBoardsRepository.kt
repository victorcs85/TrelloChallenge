package br.com.victorcs.trellochallenge.domain.repository

import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.domain.model.Response

typealias BoardsResponse = Response<List<BoardItem>>

interface IBoardsRepository {
    suspend fun getBoards(): BoardsResponse
}