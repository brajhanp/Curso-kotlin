package com.example.clase10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clase10.databinding.ActivityFormNoteBinding

class FormNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (validateForm(title, content)) {
                val note = NoteModel(title = title, content = content)
                val intent = Intent()
                intent.putExtra("NOTE_OBJECT_EXTRA", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun validateForm(title: String, content: String): Boolean {
        var isValid = true
        if (title.isEmpty()) {
            binding.tilTitle.error = "El título es obligatorio"
            isValid = false
        } else {
            binding.tilTitle.error = null
        }

        if (content.isEmpty()) {
            binding.tilContent.error = "El contenido es obligatorio"
            isValid = false
        } else {
            binding.tilContent.error = null
        }

        return isValid
    }
}
