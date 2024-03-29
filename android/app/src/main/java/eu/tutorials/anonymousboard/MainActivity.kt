package eu.tutorials.anonymousboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorials.anonymousboard.api.Server
import eu.tutorials.anonymousboard.databinding.ActivityMainBinding
import eu.tutorials.anonymousboard.dto.BoardListDTO

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<MainViewModel>()

    private var boardList: ArrayList<BoardListDTO>? = null
    private var sortStatus: Int = 0
    private var id: Long? = null
    private var alertDialog: AlertDialog? = null


//    var boardList = arrayListOf<BoardListDTO>(
//        BoardListDTO(1, "촉촉한 초코칩", "2022-06-01", 1),
//        BoardListDTO(2, "딸기케이크", "2022-06-02", 2),
//        BoardListDTO(3, "피자빵", "2022-06-03", 3),
//        BoardListDTO(4, "양념치킨", "2022-06-04", 4),
//        BoardListDTO(5, "메론", "2022-06-05", 5)
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.search.isSubmitButtonEnabled = true

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
            val boardRecyclerViewAdapter = BoardRecyclerViewAdapter(this, boardList) { board ->
                id = board.id
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("id", id)
                }
                startActivity(intent)
            }

            binding.recyclerView.adapter = boardRecyclerViewAdapter
        }

        binding.sort.setOnClickListener {
            if (sortStatus == 0) {
                binding.sort.text = "최신순"
                sortStatus = 1
                viewModel.getBoardsByViews()
            } else {
                binding.sort.text = "조회순"
                sortStatus = 0
                viewModel.getBoards()
            }
        }


        binding.write.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(title: String?): Boolean {
                // 검색 버튼 누를 때 호출
                viewModel.getBoardsByTitle(title)
                return true
            }

            override fun onQueryTextChange(title: String?): Boolean {
                // 검색창에서 글자가 변경이 일어날 때마다 호출
                viewModel.getBoardsByTitle(title)
                return true
            }
        })

        binding.server.setOnClickListener {
            val layoutInflater = LayoutInflater.from(this)
            val view = layoutInflater.inflate(R.layout.server, null)
            var now = view.findViewById<TextView>(R.id.now)
            var newServer = view.findViewById<TextView>(R.id.newServer)
            now.append(Server.url)

            alertDialog = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setView(view)
                .create()

            alertDialog!!.show()
            view.findViewById<Button>(R.id.close).setOnClickListener {
                alertDialog?.dismiss()
            }

            view.findViewById<Button>(R.id.change).setOnClickListener {
                Server.url = newServer.text.toString()
                Server.changeUrl()
                Toast.makeText(this, "변경 완료!", Toast.LENGTH_SHORT).show()
                alertDialog?.dismiss()
                now.text = Server.url
            }
        }

    }

    override fun onResume() {
        super.onResume()
        binding.sort.text = "조회순"
        sortStatus = 0
        viewModel.getBoards()
        boardList = viewModel.boards.value?.body
        val boardRecyclerViewAdapter = BoardRecyclerViewAdapter(this, boardList) { board ->
            id = board.id
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", id)
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = boardRecyclerViewAdapter
    }

    override fun onStart() {
        super.onStart()
        binding.sort.text = "조회순"
        sortStatus = 0
        viewModel.getBoards()
        boardList = viewModel.boards.value?.body
        val boardRecyclerViewAdapter = BoardRecyclerViewAdapter(this, boardList) { board ->
            id = board.id
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", id)
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = boardRecyclerViewAdapter
    }
}