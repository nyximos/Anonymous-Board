package eu.tutorials.anonymousboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.isEmpty
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

        var btnSave = binding.save
        var btnList = binding.list

        btnList.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            var title = binding.title.text
            println("title = ${title}")
            var password = binding.password.text
            println("password = ${password}")
            var content = binding.content.text
            println("content = ${content}")

            if (title.isBlank()) {
                Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if (password.isBlank()) {
                Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if (content.isBlank()) {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if (title.isNotBlank() && password.isNotBlank() && content.isNotBlank())
                viewModel.save(title.toString(), password.toString(), content.toString())

            finish()
        }
    }
}