package com.codebear.simpletakenotes.presentation

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codebear.simpletakenotes.domain.models.NoteModel
import com.codebear.simpletakenotes.databinding.ActivityFormNoteBinding

class FormNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormNoteBinding
    private var noteToEdit: NoteModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteToEdit = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("NOTE_DATA_EXTRA", NoteModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("NOTE_DATA_EXTRA")
        }

        if (noteToEdit != null) {
            binding.etTitle.setText(noteToEdit!!.title)
            binding.etContent.setText(noteToEdit!!.content)
            binding.btnCancelDelete.text = "Delete"
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val intent = Intent()
                if (noteToEdit == null) {
                    intent.putExtra("RESULT_NOTE", NoteModel(title = title, content = content, createdAt = System.currentTimeMillis()))
                    setResult(NotesActivity.RESULT_CODE_SAVE, intent)
                } else {
                    val updatedNote = noteToEdit!!.copy(title = title, content = content)
                    intent.putExtra("RESULT_NOTE", updatedNote)
                    setResult(NotesActivity.RESULT_CODE_UPDATE, intent)
                }
                finish()
            }
        }

        binding.btnCancelDelete.setOnClickListener {
            if (noteToEdit != null) {
                val intent = Intent()
                intent.putExtra("RESULT_NOTE", noteToEdit)
                setResult(NotesActivity.RESULT_CODE_DELETE, intent)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
}
