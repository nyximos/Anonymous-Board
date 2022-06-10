package eu.tutorials.anonymousboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import eu.tutorials.anonymousboard.databinding.ActivityDetailBinding
import eu.tutorials.anonymousboard.databinding.DialogBinding
import eu.tutorials.anonymousboard.dto.BoardDTO

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    var board: BoardDTO? = null
    private var contentId: Long? = null

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val dialog by lazy {
        DialogBinding.inflate(layoutInflater)
    }

    var alertDialog: AlertDialog? = null


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
            binding.id.text = board?.id.toString()
            binding.title.text = board?.title
            binding.createdAt.text = board?.createdAt
            binding.views.text = board?.views.toString()
            binding.content.text = board?.content.toString()
        }

        binding.list.setOnClickListener {
            finish()
        }

        binding.delete.setOnClickListener {
            val layoutInflater = LayoutInflater.from(this)
            val view = layoutInflater.inflate(R.layout.dialog, null)
            val password = view.findViewById<TextView>(R.id.password)
            val idText : String = binding.id.text.toString()
            val id : Long = idText.toLong()

            alertDialog = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setView(view)
                .create()

            alertDialog!!.show()
            view.findViewById<Button>(R.id.close).setOnClickListener {
                alertDialog?.dismiss()
            }

            view.findViewById<Button>(R.id.send).setOnClickListener {
//                Toast.makeText(this, "${password.text}", Toast.LENGTH_SHORT).show()
                viewModel.remove(id,password.text.toString())
                alertDialog?.dismiss()
                finish()
            }
        }

        btnUpdate.setOnClickListener {

            val idText : String = binding.id.text.toString()
            val id = idText.toLong()

            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra("id", id)
                putExtra("title", binding.title.text)
                putExtra("content", binding.content.text)
            }
            finish()
            startActivity(intent)
        }

    }
}