package eu.tutorials.anonymousboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import eu.tutorials.anonymousboard.databinding.ActivityUpdateBinding
import eu.tutorials.anonymousboard.dto.BoardDTO

class UpdateActivity : AppCompatActivity() {

    private var contentId: Long? = null
    var board: BoardDTO? = null

    private val binding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var btnUpdate = binding.update

        with(intent) {
            contentId = getLongExtra("id", 0)
        }

        contentId?.let { viewModel.getBoard(contentId!!) }

        viewModel.board.observe(this) {
            board = viewModel.board.value
            binding.title.append(board?.title)
            binding.content.append(board?.content)
        }

        binding.list.setOnClickListener {
            finish()
        }

        btnUpdate.setOnClickListener {
            var title = binding.title.text
            var password = binding.password.text
            var content = binding.content.text

            if (title.isBlank()) {
                Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if (password.isBlank()) {
                Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if (content.isBlank()) {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if (title.isNotBlank() && password.isNotBlank() && content.isNotBlank()) {
                viewModel.update(contentId, title.toString(), password.toString(), content.toString())
                finish()
            }
        }
    }


}