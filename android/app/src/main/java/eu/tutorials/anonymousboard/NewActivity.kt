package eu.tutorials.anonymousboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import eu.tutorials.anonymousboard.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.list.setOnClickListener {
            finish()
        }

        binding.save.setOnClickListener {
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
                viewModel.save(title.toString(), password.toString(), content.toString())
                finish()
            }
        }
    }
}