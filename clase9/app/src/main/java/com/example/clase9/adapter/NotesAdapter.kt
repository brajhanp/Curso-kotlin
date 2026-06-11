package com.example.clase9.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clase9.databinding.ItemNoteBinding
import com.example.clase9.model.NoteModel

class NotesAdapter(private var notes: List<NoteModel> = emptyList()) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvNoteTitle.text = note.title
        holder.binding.tvNoteContent.text = note.content
    }

    override fun getItemCount(): Int = notes.size

    fun updateList(newList: List<NoteModel>) {
        this.notes = newList
        notifyDataSetChanged()
    }
}
