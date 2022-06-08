package eu.tutorials.anonymousboard.dto

import java.time.LocalDateTime

data class BoardListDTO (
    val id: Long,
    val title: String,
    val createdAt: String,
    val views: Int
)