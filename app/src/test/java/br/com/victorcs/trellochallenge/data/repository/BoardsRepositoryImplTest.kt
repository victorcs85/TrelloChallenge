package br.com.victorcs.trellochallenge.data.repository

import androidx.test.filters.SmallTest
import br.com.victorcs.trellochallenge.base.CoroutineTestRule
import br.com.victorcs.trellochallenge.data.dto.BoardItemDto
import br.com.victorcs.trellochallenge.data.source.remote.TrelloService
import br.com.victorcs.trellochallenge.domain.mapper.DomainMapper
import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.domain.model.Response
import br.com.victorcs.trellochallenge.domain.repository.IBoardsRepository
import br.com.victorcs.trellochallenge.shared.test.MockTests
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class BoardsRepositoryImplTest {

    private val remoteDataService = mockk<TrelloService>(relaxed = true)
    private val mapper = mockk<DomainMapper<BoardItemDto, BoardItem>>(relaxed = true)

    private lateinit var repository: IBoardsRepository

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Before
    fun setup() {
        repository = BoardsRepositoryImpl(remoteDataService, mapper)
    }

    @Test
    fun givenValidKeyToken_whenGetBoardsData_thenReturnBoardsSuccessfully() = runTest {
        val responseMock = MockTests.boardItemsDtoMock
        val domainMock = MockTests.boardItemsMock

        coEvery { remoteDataService.getBoards(key = any(), token = any()) } returns responseMock
        every { mapper.toDomain(responseMock) } returns domainMock

        val result = repository.getBoards()

        assert(result is Response.Success && result.data == domainMock)
    }

    @Test
    fun givenInvalidKeyToken_whenGetBoardsData_thenReturnError() = runTest {
        val responseMock = MockTests.boardItemsDtoMock
        val domainMock = MockTests.boardItemsMock

        coEvery {
            remoteDataService.getBoards(
                key = any(),
                token = any()
            )
        } throws MockTests.genericErrorMock
        every { mapper.toDomain(responseMock) } returns domainMock

        val result = repository.getBoards()

        assert(result is Response.Error && result.errorMessage.contains(MockTests.GENERIC_ERROR))
    }

    @Test
    fun givenWithoutNetwork_whenGetBoardsData_thenReturnError() = runTest {
        val responseMock = MockTests.boardItemsDtoMock
        val domainMock = MockTests.boardItemsMock

        coEvery {
            remoteDataService.getBoards(
                key = any(),
                token = any()
            )
        } throws MockTests.errorNetworkMock
        every { mapper.toDomain(responseMock) } returns domainMock

        val result = repository.getBoards()

        assert(result is Response.Error && result.errorMessage.contains(MockTests.NETWORK_ERROR))
    }

}