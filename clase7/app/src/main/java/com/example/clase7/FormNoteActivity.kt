package com.example.clase7

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.clase7.databinding.ActivityFormNoteBinding

class FormNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("NOTE_OBJECT_EXTRA", NoteModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("NOTE_OBJECT_EXTRA")
        }

        val isUpdate = note != null
        setupUI(isUpdate)

        if (isUpdate) {
            binding.etTitle.setText(note?.title)
            binding.etContent.setText(note?.content)
        }

        binding.ivBack.setOnClickListener { finish() }
        binding.btnCancel.setOnClickListener { finish() }

        binding.btnSaveNote.setOnClickListener {
            if (isValidForm()) {
                val newNote = NoteModel(
                    id = (0..1000).random(),
                    title = binding.etTitle.text.toString(),
                    content = binding.etContent.text.toString(),
                    date = "24/10/2023"
                )
                val resultIntent = Intent().apply {
                    putExtra("NOTE_OBJECT_EXTRA", newNote)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        binding.btnUpdate.setOnClickListener {
            if (isValidForm()) {
                val updatedNote = NoteModel(
                    id = note?.id ?: 0,
                    title = binding.etTitle.text.toString(),
                    content = binding.etContent.text.toString(),
                    date = note?.date ?: "24/10/2023"
                )
                val resultIntent = Intent().apply {
                    putExtra("NOTE_OBJECT_EXTRA", updatedNote)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun setupUI(isUpdate: Boolean) {
        if (isUpdate) {
            binding.tvFormTitle.text = getString(R.string.label_update)
            binding.llUpdateButtons.visibility = View.VISIBLE
            binding.btnSaveNote.visibility = View.GONE
        } else {
            binding.tvFormTitle.text = getString(R.string.label_register)
            binding.llUpdateButtons.visibility = View.GONE
            binding.btnSaveNote.visibility = View.VISIBLE
        }
    }

    private fun isValidForm(): Boolean {
        var isValid = true
        if (binding.etTitle.text.toString().isBlank()) {
            binding.tilTitle.error = getString(R.string.error_required)
            isValid = false
        } else {
            binding.tilTitle.error = null
        }

        if (binding.etContent.text.toString().isBlank()) {
            binding.tilContent.error = getString(R.string.error_required)
            isValid = false
        } else {
            binding.tilContent.error = null
        }
        return isValid
    }
}
