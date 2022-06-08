package eu.tutorials.anonymousboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorials.anonymousboard.databinding.ActivityMainBinding
import eu.tutorials.anonymousboard.dto.BoardListDTO

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<MainViewModel>()

    private var boardList: ArrayList<BoardListDTO>? = null
    private var btnSort: Button? = null
    private var sortStatus: Int = 0

//    var boardList = arrayListOf<BoardListDTO>(
//        BoardListDTO(1, "놀고싶당", "2022-06-01", 1),
//        BoardListDTO(2, "테스트", "2022-06-02", 2),
//        BoardListDTO(3, "피자빵", "2022-06-03", 3),
//        BoardListDTO(4, "양념치킨", "2022-06-04", 4),
//        BoardListDTO(5, "메론", "2022-06-05", 5)
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnSort = findViewById(R.id.sort)

        // 게시글 전체 조회 실행
        viewModel.getBoards()

/*        RecyclerView
          1. Layout Manager : View를 그려줌
          2. adapter(RecyclerView) : 효율적으로 데이터와 뷰 관리
*/
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        // viewModel 클래스에 boards가 바뀌면 실행
        viewModel.boards.observe(this) {
            boardList = viewModel.boards.value?.body
            Log.d("RESPONSE", "${it.toString()}")
            val boardRecyclerViewAdapter = BoardRecyclerViewAdapter(this, boardList) { board->
                Toast.makeText(this, "제목은 ${board.title} 입니다", Toast.LENGTH_SHORT).show()
            }
            binding.recyclerView.adapter = boardRecyclerViewAdapter
        }

        btnSort?.setOnClickListener{
            if (sortStatus == 0) {
                binding.sort.text = "조회순"
                sortStatus = 1
                viewModel.getBoardsByViews()
            } else {
                binding.sort.text = "최신순"
                sortStatus = 0
                viewModel.getBoards()
            }
        }

    }
}