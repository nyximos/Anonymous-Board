package eu.tutorials.anonymousboard.dto

import java.time.LocalDateTime

// Model 역할을 할 data class
// 변수이름을 다르게하고 싶거나, json 파일의 이름이 한글이거나 할 경우엔 @SerializedName 어노테이션을 사용하여 역/직렬화
data class BoardDTO(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val views: Int
)
