package br.com.victorcs.trellochallenge.presentation.features.boards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import br.com.victorcs.trellochallenge.base.BaseViewModelTest
import br.com.victorcs.trellochallenge.base.CoroutineTestRule
import br.com.victorcs.trellochallenge.domain.usecases.IGetBoardsUseCase
import br.com.victorcs.trellochallenge.shared.test.MockTests
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@SmallTest
class BoardsViewModelTest : BaseViewModelTest() {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val useCase = mockk<IGetBoardsUseCase>(relaxed = true)

    private lateinit var viewModel: BoardsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = BoardsViewModel(useCase = useCase, dispatchers = testDispatcherProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenValidFlow_whenGetBoardsData_thenReturnBoardsSuccessfully() =
        runTest {

            val responseMock = MockTests.boardItemsResponseMock
            val expectedState = MockTests.boardScreenStateMock

            coEvery {
                useCase.invoke()
            } returns responseMock

            viewModel.screenState.test {
                viewModel.execute(BoardsIntent.FetchBoards)

                awaitItem()

                val finalState = awaitItem()

                assertEquals(
                    expectedState.boards,
                    finalState.boards
                )
                assertNull(finalState.errorMessage)
                assertFalse(finalState.isLoading)

                cancelAndIgnoreRemainingEvents()
            }

            coEvery { useCase.invoke() }
        }

    @Test
    fun givenInvalidFlow_whenGetBoardsData_thenReturnError() =
        runTest {

            val responseMock = MockTests.genericResponseErrorMock

            coEvery {
                useCase.invoke()
            } returns responseMock



            viewModel.screenState.test {
                viewModel.execute(BoardsIntent.FetchBoards)

                awaitItem()

                val finalState = awaitItem()
                assertEquals(MockTests.GENERIC_ERROR, finalState.errorMessage)
                assertTrue(finalState.boards == null)
                assertFalse(finalState.isLoading)

                cancelAndIgnoreRemainingEvents()
            }

            coEvery { useCase.invoke() }
        }

}