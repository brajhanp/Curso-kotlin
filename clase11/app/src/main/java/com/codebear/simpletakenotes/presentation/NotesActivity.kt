package com.codebear.simpletakenotes.presentation

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codebear.simpletakenotes.domain.models.NoteModel
import com.codebear.simpletakenotes.databinding.ActivityNotesBinding
import kotlinx.coroutines.launch

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private val vm by viewModels<NotesVM>()
    private var adapter: NotesAdapter? = null

    companion object {
        const val RESULT_CODE_SAVE = 100
        const val RESULT_CODE_UPDATE = 101
        const val RESULT_CODE_DELETE = 102
    }

    private val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_CODE_SAVE || result.resultCode == RESULT_CODE_UPDATE || result.resultCode == RESULT_CODE_DELETE) {
            val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra("RESULT_NOTE", NoteModel::class.java)
            } else {
                @Suppress("DEPRECATION")
                result.data?.getParcelableExtra("RESULT_NOTE")
            }

            data?.let {
                when (result.resultCode) {
                    RESULT_CODE_SAVE -> vm.insertNote(it.title, it.content)
                    RESULT_CODE_UPDATE -> vm.updateNote(it)
                    RESULT_CODE_DELETE -> vm.deleteNote(it)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            resultForm.launch(intent)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.notesStateFlow.collect { notes ->
                    adapter?.updateItems(notes)
                    if (notes.isEmpty()) {
                        binding.llEmptyMessageState.visibility = View.VISIBLE
                        binding.rvNotes.visibility = View.GONE
                    } else {
                        binding.llEmptyMessageState.visibility = View.GONE
                        binding.rvNotes.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = NotesAdapter(emptyList()) { note ->
            val intent = Intent(this, FormNoteActivity::class.java)
            intent.putExtra("NOTE_DATA_EXTRA", note)
            resultForm.launch(intent)
        }
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter
    }
}
