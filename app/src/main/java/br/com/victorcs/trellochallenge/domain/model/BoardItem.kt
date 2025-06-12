package br.com.victorcs.trellochallenge.domain.model

data class BoardItem(
    val id: String,
    val name: String,
    val desc: String,
    val closed: Boolean
)
