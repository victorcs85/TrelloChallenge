package br.com.victorcs.trellochallenge.data.source.remote

import br.com.victorcs.trellochallenge.data.dto.BoardItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TrelloService {

    @GET("boards")
    suspend fun getBoards(
        @Query("key") key: String,
        @Query("token") token: String
    ): List<BoardItemDto>

}
