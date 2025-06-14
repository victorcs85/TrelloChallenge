package br.com.victorcs.trellochallenge.domain.usecases

import androidx.test.filters.SmallTest
import br.com.victorcs.trellochallenge.base.CoroutineTestRule
import br.com.victorcs.trellochallenge.domain.model.Response
import br.com.victorcs.trellochallenge.domain.repository.IBoardsRepository
import br.com.victorcs.trellochallenge.shared.test.MockTests
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class GetBoardsUseCaseImplTest {

    private val repository = mockk<IBoardsRepository>(relaxed = true)

    private lateinit var useCase: IGetBoardsUseCase

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Before
    fun setup() {
        useCase = GetBoardsUseCaseImpl(repository)
    }

    @Test
    fun givenValidFlow_whenGetBoardsData_thenReturnBoardsSuccessfully() = runTest {
        val responseMock = MockTests.boardItemsResponseMock
        val repositoryResponseMock = Response.Success(MockTests.boardItemsMock)

        coEvery { useCase.invoke() } returns responseMock
        coEvery { repository.getBoards() } returns repositoryResponseMock

        val result = repository.getBoards()

        assert(result is Response.Success && result.data == responseMock.data)
    }

    @Test
    fun givenInvalidFlow_whenGetBoardsData_thenReturnBoardsError() = runTest {
        coEvery { repository.getBoards() } returns MockTests.genericResponseErrorMock

        val result = useCase.invoke()

        assert(result is Response.Error && result.errorMessage.contains(MockTests.GENERIC_ERROR))
    }
}