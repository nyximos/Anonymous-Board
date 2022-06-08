package eu.tutorials.anonymousboard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import eu.tutorials.anonymousboard.api.JsServer
import eu.tutorials.anonymousboard.dto.BoardListDTOs
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val error = MutableLiveData<String>()
    val board = MutableLiveData<Board>()
    val boards = MutableLiveData<BoardListDTOs>()

    lateinit var request: Call<Board>

    fun getBoards() = viewModelScope.launch {
        val request = JsServer.boardApi.getBoards()
        request.enqueue(object : Callback<BoardListDTOs> {

            override fun onResponse(call: Call<BoardListDTOs>, response: Response<BoardListDTOs>) {
                boards.value = response.body()
                Log.d("RESPONSE", "성공 : ${response.raw()}")
                Log.i("RESPONSE", "게시글 전체 조회 성공 : ${response.body()}")

            }

            override fun onFailure(call: Call<BoardListDTOs>, t: Throwable) {
                error.value = t.localizedMessage
                Log.d("RESPONSE", "실패 : $t")
            }
        })
    }

    fun getBoard(id: Long) = viewModelScope.launch {
        val request = JsServer.boardApi.getBoard(id)
        request.enqueue(object : Callback<Board> {

            //            응답
            override fun onResponse(call: Call<Board>, response: Response<Board>) {
                board.value = response.body()
            }

            //            실패
            override fun onFailure(call: Call<Board>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        if (::request.isInitialized) request.cancel()
    }
}
