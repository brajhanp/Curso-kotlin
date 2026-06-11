package com.example.clase9.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clase9.databinding.ActivityAddNoteBinding
import com.example.clase9.model.NoteModel

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val note = NoteModel(title = title, content = content)
                val intent = Intent().apply {
                    putExtra("EXTRA_NOTE", note)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
