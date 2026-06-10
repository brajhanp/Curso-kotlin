package com.example.clase5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MVCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)

        val etNote = findViewById<EditText>(R.id.etContent)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val text = etNote.text.toString()
            NotesDb.notes.add(
                Note(
                    content = text,
                    id = NotesDb.notes.size + 1,
                ),
            )
            showNotes()
        }
    }

    private fun showNotes() {
        Log.e("TAG", "showNotes: ${NotesDb.notes}")
    }
}
