package eu.tutorials.anonymousboard.dto

// Model 역할을 할 data class
// 변수이름을 다르게하고 싶거나, json 파일의 이름이 한글이거나 할 경우엔 @SerializedName 어노테이션을 사용하여 역/직렬화
data class BoardListDTOs(
    var header: String,
    var message: String,
    var body: ArrayList<BoardListDTO>
)
