package com.example.clase9.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clase9.model.NoteModel

class NotesViewModel : ViewModel() {

    // Lista mutable interna para simular persistencia
    private val notes = mutableListOf<NoteModel>()

    /**
     * ERROR DE MEMORIA CORREGIDO:
     * NO debemos pasar la referencia directa de 'notes' al LiveData/Adapter.
     * Si pasamos la referencia, el Adapter y el ViewModel apuntarán al mismo espacio en RAM,
     * lo que puede causar que los datos se dupliquen o no se refresquen correctamente
     * al manipular la lista original. Usamos .toList() para crear una copia.
     */
    private val _notesOBS = MutableLiveData<List<NoteModel>>()
    val notesOBS: LiveData<List<NoteModel>> get() = _notesOBS

    fun addNote(title: String, content: String) {
        val newId = notes.size + 1
        val newNote = NoteModel(id = newId, title = title, content = content)
        
        notes.add(newNote)
        
        // Publicamos una COPIA de la lista para romper la referencia de memoria
        _notesOBS.value = notes.toList()
    }
}
