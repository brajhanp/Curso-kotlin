package com.codebear.simpletakenotes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.codebear.simpletakenotes.data.AppDatabase
import com.codebear.simpletakenotes.domain.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesVM(application: Application) : AndroidViewModel(application) {

    private val noteDao = AppDatabase.getInstance(application).noteDao()

    private val _notesStateFlow = MutableStateFlow<List<NoteModel>>(emptyList())
    val notesStateFlow: StateFlow<List<NoteModel>> get() = _notesStateFlow

    init {
        observeNotes()
    }

    private fun observeNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.getAllNotes().collectLatest { notes ->
                _notesStateFlow.value = notes
            }
        }
    }

    fun insertNote(title: String, content: String) = viewModelScope.launch(Dispatchers.IO) {
        noteDao.insertNote(
            NoteModel(
                title = title,
                content = content,
                createdAt = System.currentTimeMillis()
            )
        )
    }

    fun updateNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        noteDao.updateNote(note)
    }

    fun deleteNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        noteDao.deleteNote(note)
    }
}
