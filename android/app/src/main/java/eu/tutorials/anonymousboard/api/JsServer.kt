package eu.tutorials.anonymousboard.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsServer {
    companion object{
        private const val url = "http://172.26.14.249:8088"
//        private const val url = "http://10.0.2.2:8088"
        private var server:Retrofit=Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()) // Json 데이터를 사용자가 정의한 Java 객채로 변환해주는 라이브러리
            .build()
        val boardApi: BoardApi = server.create(BoardApi::class.java) // retrofit 객체 만듦
    }
}