package com.example.clase10

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clase10.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private val notesVM: NotesViewModel by viewModels()
    private lateinit var adapter: NotesAdapter

    private val formLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                data?.getParcelableExtra("NOTE_OBJECT_EXTRA", NoteModel::class.java)
            } else {
                @Suppress("DEPRECATION")
                data?.getParcelableExtra("NOTE_OBJECT_EXTRA")
            }

            note?.let {
                notesVM.insertNoteWithFakeDelay(it.title, it.content)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observeViewModel()

        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            formLauncher.launch(intent)
        }
    }

    private fun initRecyclerView() {
        adapter = NotesAdapter(emptyList())
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter
    }

    private fun observeViewModel() {
        notesVM.notesOBS.observe(this) { notes ->
            if (notes.isEmpty()) {
                binding.llEmptyMessageState.visibility = View.VISIBLE
                binding.rvNotes.visibility = View.GONE
            } else {
                binding.llEmptyMessageState.visibility = View.GONE
                binding.rvNotes.visibility = View.VISIBLE
                adapter.updateItems(notes)
            }
        }
    }
}
