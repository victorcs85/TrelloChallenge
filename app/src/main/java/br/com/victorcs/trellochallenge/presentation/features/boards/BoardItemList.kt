package br.com.victorcs.trellochallenge.presentation.features.boards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.presentation.theme.LIGHT_GRAY_COLOR
import br.com.victorcs.trellochallenge.presentation.theme.WHITE_COLOR

const val BOARD_POSITION_TEST_TAG = "board_position_test_tag"
const val BOARD_NAME_TEST_TAG = "board_name_test_tag"
const val BOARD_DESC_TEST_TAG = "board_desc_test_tag"

@Composable
fun BoardItemList(boardItem: BoardItem, onClick: () -> Unit) {

    val backgroundColor = if (boardItem.closed) {
        WHITE_COLOR
    } else {
        LIGHT_GRAY_COLOR
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
            .semantics {
                contentDescription = boardItem.name
                role = Role.Button
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .testTag(BOARD_POSITION_TEST_TAG),
                text = "${boardItem.position}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                boardItem.name.takeIf { it.isNotBlank() }?.let { name ->
                    Text(
                        modifier = Modifier.testTag(BOARD_NAME_TEST_TAG),
                        text = "$name ${boardItem.position}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                boardItem.desc.takeIf { it.isNotBlank() }?.let { desc ->
                    Text(
                        modifier = Modifier.testTag(BOARD_DESC_TEST_TAG),
                        text = desc,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun BoardItemPreview() {
    BoardItemList(
        boardItem = getMockExchange(),
        onClick = {},
    )
}

private fun getMockExchange() = BoardItem(
    id = "1",
    name = "Board",
    desc = "Description 1",
    closed = false,
    position = 1,
)
