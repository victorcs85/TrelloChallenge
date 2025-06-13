package br.com.victorcs.trellochallenge.data.repository

import br.com.victorcs.trellochallenge.BuildConfig
import br.com.victorcs.trellochallenge.core.extensions.safeApiCall
import br.com.victorcs.trellochallenge.data.dto.BoardItemDto
import br.com.victorcs.trellochallenge.data.source.remote.TrelloService
import br.com.victorcs.trellochallenge.domain.mapper.DomainMapper
import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.domain.repository.BoardsResponse
import br.com.victorcs.trellochallenge.domain.repository.IBoardsRepository

class BoardsRepositoryImpl(
    private val service: TrelloService,
    private val mapper: DomainMapper<BoardItemDto, BoardItem>,
) : IBoardsRepository {
    override suspend fun getBoards(): BoardsResponse =
        safeApiCall {
            val response = service.getBoards(
                key = BuildConfig.TRELLO_KEY,
                token = BuildConfig.TRELLO_TOKEN
            )
            mapper.toDomain(response)
        }
}
