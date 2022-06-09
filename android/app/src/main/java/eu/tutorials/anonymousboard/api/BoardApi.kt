package eu.tutorials.anonymousboard.api

import eu.tutorials.anonymousboard.dto.BoardListResponseDTO
import eu.tutorials.anonymousboard.dto.BoardResponseDTO
import eu.tutorials.anonymousboard.dto.ResponseDTO
import eu.tutorials.anonymousboard.dto.BoardFormDTO
import retrofit2.Call
import retrofit2.http.*


/*
@GET 요청 메서드에는 RetrofitClient에 작성한 baseUrl의 뒤에 오는 EndPoint를 지정하고,
동적으로 변경해야 하는 파라미터는 @Query 어노테이션을 이용해서 메서드를 호출할 때 값을 넘겨받아 주소에 포함시키도록 합니다.
마지막으로, 요청한 주소로부터 Json을 반환받는다면 POJO Class로 받을 수 있도록 Call를 return type으로 정해줍니다.
 */
interface BoardApi {

    @GET("/api/boards")
    fun getBoards(): Call<BoardListResponseDTO>

    @GET("/api/boards/views")
    fun getBoardsByViews(): Call<BoardListResponseDTO>

    @GET("/api/boards/{id}")
    fun getBoard(@Path("id") id:Long): Call<BoardResponseDTO>

    @GET("/api/boards/title")
    fun getBoardsByTitle(@Query("title") title: String?): Call<BoardListResponseDTO>

    @POST("/api/boards")
    fun save(@Body boardFormDTO: BoardFormDTO): Call<ResponseDTO>
}