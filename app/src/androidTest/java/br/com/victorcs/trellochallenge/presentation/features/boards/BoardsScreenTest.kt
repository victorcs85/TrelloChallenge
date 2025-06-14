package br.com.victorcs.trellochallenge.presentation.features.boards

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.test.filters.MediumTest
import br.com.victorcs.trellochallenge.domain.usecases.IGetBoardsUseCase
import br.com.victorcs.trellochallenge.presentation.MainActivity
import br.com.victorcs.trellochallenge.presentation.utils.TestDispatchersProvider
import br.com.victorcs.trellochallenge.presentation.views.EMPTY_INFO_LIST_VIEW
import br.com.victorcs.trellochallenge.presentation.views.ERROR_MESSAGE_VIEW
import br.com.victorcs.trellochallenge.shared.test.MockTests
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
class BoardsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: BoardsViewModel

    private val useCase = mockk<IGetBoardsUseCase>()

    @Before
    fun setUp() {
        viewModel = BoardsViewModel(useCase, TestDispatchersProvider)

        coEvery { useCase.invoke() } returns MockTests.boardItemsResponseMock

        composeTestRule.activity.setContent {
            val state = viewModel.screenState.collectAsStateWithLifecycle().value

            BoardsScreen(
                state,
                execute = viewModel::execute,
            )
        }
    }

    @Test
    fun givenScreen_whenLoadedData_thenSuccessfullyData() {

        composeTestRule.run {
            onNodeWithTag(BOARD_POSITION_TEST_TAG).isDisplayed()
            onNodeWithTag(BOARD_NAME_TEST_TAG).isDisplayed()
            onNodeWithTag(BOARD_DESC_TEST_TAG).isDisplayed()
            onNodeWithText(MockTests.BOARD_NAME_ONE).assertIsDisplayed()
        }
    }

    @Test
    fun givenEmptyData_whenLoadedData_thenSShowError() {
        coEvery { useCase.invoke() } returns MockTests.emptyResponseMock

        composeTestRule.run {
            onNodeWithTag(BOARD_POSITION_TEST_TAG).isNotDisplayed()
            onNodeWithTag(BOARD_NAME_TEST_TAG).isNotDisplayed()
            onNodeWithTag(BOARD_DESC_TEST_TAG).isNotDisplayed()
            onNodeWithTag(EMPTY_INFO_LIST_VIEW).isDisplayed()
            onNodeWithText(MockTests.EMPTY_DATA_ERROR).assertIsDisplayed()
        }
    }

    @Test
    fun givenError_whenLoadedData_thenSShowError() {
        coEvery { useCase.invoke() } returns MockTests.genericResponseErrorMock

        composeTestRule.run {
            onNodeWithTag(BOARD_POSITION_TEST_TAG).isNotDisplayed()
            onNodeWithTag(BOARD_NAME_TEST_TAG).isNotDisplayed()
            onNodeWithTag(BOARD_DESC_TEST_TAG).isNotDisplayed()
            onNodeWithTag(ERROR_MESSAGE_VIEW).isDisplayed()
            onNodeWithText(MockTests.GENERIC_ERROR).assertIsDisplayed()
        }
    }
}
