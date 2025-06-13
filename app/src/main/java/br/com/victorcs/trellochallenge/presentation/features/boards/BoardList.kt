package br.com.victorcs.trellochallenge.presentation.features.boards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.presentation.theme.LIGHT_GRAY_COLOR

@Composable
fun BoardList(
    boards: List<BoardItem>,
    listState: LazyListState,
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = boards.size,
            key = { boards[it].id },
            itemContent = { index ->
                val boardItem = boards[index]

                BoardItemList (boardItem) { }

                if (index < boards.lastIndex) {
                    HorizontalDivider(color = LIGHT_GRAY_COLOR, thickness = 1.dp)
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoardListPreview() {
    val listState = rememberLazyListState()

    BoardList(
        getMockBoardList(),
        listState,
    )
}

private fun getMockBoardList(): List<BoardItem> {
    return listOf(
        BoardItem(id = "1", name = "Board", desc = "Board de teste 1", position = 1, closed = false),
        BoardItem(id = "2", name = "Board", desc = "Board de teste 2", position = 2, closed = true),
        BoardItem(id = "3", name = "Board", desc = "Board de teste 3", position = 3, closed = false),
    )
}
