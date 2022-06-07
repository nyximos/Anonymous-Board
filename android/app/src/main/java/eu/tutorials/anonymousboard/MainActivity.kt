package eu.tutorials.anonymousboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorials.anonymousboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<MainViewModel>()

    private val adapter = BoardRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//
////        RecyclerView 초기화
//        val manager = LinearLayoutManager(this)  // manager로 View를 그려준다.
//        binding.recyclerView.layoutManager = manager
//        binding.recyclerView.adapter = adapter
//
////        RecyclerView에 데이터 전달하기
//
//        viewModel.board.observe(this){
//            Log.d("RESPONSE", "${it.toString()}")
////            adapter.setData()
//        }
//        viewModel.getBoard(1)
    }
}