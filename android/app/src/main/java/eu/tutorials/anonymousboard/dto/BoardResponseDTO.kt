package eu.tutorials.anonymousboard.dto

data class BoardResponseDTO(
    var header: String,
    var message: String,
    var body: BoardDTO
)