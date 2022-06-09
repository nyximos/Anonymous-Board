package eu.tutorials.anonymousboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import eu.tutorials.anonymousboard.api.JsServer
import eu.tutorials.anonymousboard.dto.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val error = MutableLiveData<String>()
    val board = MutableLiveData<BoardDTO>()
    val boards = MutableLiveData<BoardListResponseDTO>()

    lateinit var request: Call<BoardDTO>

    fun getBoards() = viewModelScope.launch {
        val request = JsServer.boardApi.getBoards()
        request.enqueue(object : Callback<BoardListResponseDTO> {

            override fun onResponse(call: Call<BoardListResponseDTO>, response: Response<BoardListResponseDTO>) {
                if(response.isSuccessful) {
                    boards.value = response.body()
                    Log.d("RESPONSE", "성공 : ${response.raw()}")
                    Log.i("RESPONSE", "게시글 전체 조회 성공 : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<BoardListResponseDTO>, t: Throwable) {
                error.value = t.localizedMessage
                Log.d("RESPONSE", "실패 : $t")
            }
        })
    }

    fun getBoardsByViews() {
        val request = JsServer.boardApi.getBoardsByViews()
        request.enqueue(object : Callback<BoardListResponseDTO> {

            override fun onResponse(call: Call<BoardListResponseDTO>, response: Response<BoardListResponseDTO>) {
                if(response.isSuccessful) {
                    boards.value = response.body()
                    Log.d("RESPONSE", "성공 : ${response.raw()}")
                    Log.i("RESPONSE", "게시글 전체 조회 성공 : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<BoardListResponseDTO>, t: Throwable) {
                error.value = t.localizedMessage
                Log.d("RESPONSE", "실패 : $t")
            }
        })
    }


    fun getBoardsByTitle(title: String?) {
        val request = JsServer.boardApi.getBoardsByTitle(title)
        request.enqueue(object : Callback<BoardListResponseDTO> {

            override fun onResponse(call: Call<BoardListResponseDTO>, response: Response<BoardListResponseDTO>) {
                if(response.isSuccessful) {
                    boards.value = response.body()
                    Log.d("RESPONSE", "성공 : ${response.raw()}")
                    Log.i("RESPONSE", "게시글 조회 성공 : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<BoardListResponseDTO>, t: Throwable) {
                error.value = t.localizedMessage
                Log.d("RESPONSE", "실패 : $t")
            }
        })
    }

    fun getBoard(id: Long) = viewModelScope.launch {
        val request = JsServer.boardApi.getBoard(id)
        request.enqueue(object : Callback<BoardResponseDTO> {

            //            응답
            override fun onResponse(call: Call<BoardResponseDTO>, response: Response<BoardResponseDTO>) {
                if(response.isSuccessful){
                    board.value = response.body()?.body
                }
            }

            //            실패
            override fun onFailure(call: Call<BoardResponseDTO>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }

    fun save(title: String, password: String, content: String){
        val boardFormDto = BoardFormDTO(title, password, content)
        val request = JsServer.boardApi.save(boardFormDto)
        request.enqueue(object : Callback<ResponseDTO>{
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                Log.d("RESPONSE", "성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                error.value = t.localizedMessage
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        if (::request.isInitialized) request.cancel()
    }

}
