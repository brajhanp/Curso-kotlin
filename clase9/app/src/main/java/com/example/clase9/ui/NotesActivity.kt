package com.example.clase9.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clase9.adapter.NotesAdapter
import com.example.clase9.databinding.ActivityNotesBinding
import com.example.clase9.model.NoteModel
import com.example.clase9.viewmodel.NotesViewModel

class NotesActivity : AppCompatActivity() {

    // Instancia del ViewModel usando el delegado moderno de Activity KTX
    private val notesVM: NotesViewModel by viewModels()

    /* 
     * Referencia histórica (Método clásico):
     * private lateinit var notesVM: NotesViewModel
     * notesVM = ViewModelProvider(this).get(NotesViewModel::class.java)
     */

    private lateinit var binding: ActivityNotesBinding
    private val notesAdapter = NotesAdapter()

    private val addNoteLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            
            // Retrocompatibilidad SDK 33 (Tiramisu)
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                data?.getParcelableExtra("EXTRA_NOTE", NoteModel::class.java)
            } else {
                @Suppress("DEPRECATION")
                data?.getParcelableExtra("EXTRA_NOTE")
            }

            note?.let {
                // La Activity ya no modifica la lista ni llama a notifyDataSetChanged.
                // Todo fluye a través del ViewModel y el Observer.
                notesVM.addNote(it.title, it.content)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()

        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            addNoteLauncher.launch(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(this@NotesActivity)
            adapter = notesAdapter
        }
    }

    private fun setupObservers() {
        // Lógica reactiva: el observer se encarga de actualizar la UI
        notesVM.notesOBS.observe(this) { listaDeNotas ->
            if (listaDeNotas.isEmpty()) {
                binding.llEmptyMessageState.visibility = View.VISIBLE
                binding.rvNotes.visibility = View.GONE
            } else {
                binding.llEmptyMessageState.visibility = View.GONE
                binding.rvNotes.visibility = View.VISIBLE
                notesAdapter.updateList(listaDeNotas)
            }
        }
    }
}
