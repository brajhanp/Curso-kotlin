package com.example.clase7

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.clase7.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private val tag = "FormNotesDebug"

    private val formLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra("NOTE_OBJECT_EXTRA", NoteModel::class.java)
            } else {
                @Suppress("DEPRECATION")
                result.data?.getParcelableExtra("NOTE_OBJECT_EXTRA")
            }

            note?.let {
                Log.d(tag, "ID: ${it.id}")
                Log.d(tag, "Title: ${it.title}")
                Log.d(tag, "Content: ${it.content}")
                Log.d(tag, "Date: ${it.date}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            formLauncher.launch(intent)
        }

        // Simulación inicial
        val notes = NotesDatabase().getFakeNotes()
        if (notes.isEmpty()) {
            ocultarLista()
        } else {
            mostrarLista()
        }
    }

    private fun mostrarLista() {
        binding.rvNotes.visibility = View.VISIBLE
        binding.llEmptyMessageState.visibility = View.GONE
    }

    private fun ocultarLista() {
        binding.rvNotes.visibility = View.GONE
        binding.llEmptyMessageState.visibility = View.VISIBLE
    }
}
