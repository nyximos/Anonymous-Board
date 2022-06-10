package eu.tutorials.anonymousboard.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Server {
    companion object{
        var url = "http://172.26.28.54:8088"
//        var url = "http://10.0.2.2:8088"
        private var server:Retrofit=Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()) // Json 데이터를 사용자가 정의한 Java 객채로 변환해주는 라이브러리
            .build()
        var boardApi: BoardApi = server.create(BoardApi::class.java) // retrofit 객체 만듦


        fun changeUrl() {
            server = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            boardApi = server.create(BoardApi::class.java)
        }
    }

}