package eu.tutorials.anonymousboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import eu.tutorials.anonymousboard.databinding.ActivityDetailBinding
import eu.tutorials.anonymousboard.dto.BoardDTO

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    var board: BoardDTO? = null
    private var contentId: Long? = null

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(intent) {
            contentId = getLongExtra("id", 0)
        }
        contentId?.let { viewModel.getBoard(contentId!!) }

        viewModel.board.observe(this) {
            board = viewModel.board.value
            binding.id.text = board?.id.toString()
            binding.title.text = board?.title
            binding.createdAt.text = board?.createdAt
            binding.views.text = board?.views.toString()
            binding.content.text = board?.content.toString()
        }

        binding.list.setOnClickListener{
            finish()
        }
    }
}