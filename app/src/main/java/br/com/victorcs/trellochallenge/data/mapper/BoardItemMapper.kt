package br.com.victorcs.trellochallenge.data.mapper

import br.com.victorcs.trellochallenge.core.extensions.orFalse
import br.com.victorcs.trellochallenge.data.dto.BoardItemDto
import br.com.victorcs.trellochallenge.domain.mapper.DomainMapper
import br.com.victorcs.trellochallenge.domain.model.BoardItem

class BoardItemMapper : DomainMapper<BoardItemDto, BoardItem> {

    override fun toDomain(from: BoardItemDto): BoardItem = with(from) {
        BoardItem(
            id = id.orEmpty(),
            name = name.orEmpty(),
            desc = desc.orEmpty(),
            closed = closed.orFalse(),
        )
    }
}
