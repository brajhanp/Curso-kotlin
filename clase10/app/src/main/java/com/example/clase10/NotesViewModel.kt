package com.example.clase10

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel : ViewModel() {

    private val _notesList = mutableListOf<NoteModel>()

    private val _notesOBS = MutableLiveData<List<NoteModel>>(emptyList())
    val notesOBS: LiveData<List<NoteModel>> get() = _notesOBS

    fun insertNoteWithFakeDelay(title: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            val newId = _notesList.size + 1
            val newNote = NoteModel(id = newId, title = title, content = content)
            _notesList.add(newNote)

            // SOLUCIÓN AL CRASH DE HILOS (withContext)
            withContext(Dispatchers.Main) {
                _notesOBS.value = _notesList.toList()
            }
        }
    }
}
