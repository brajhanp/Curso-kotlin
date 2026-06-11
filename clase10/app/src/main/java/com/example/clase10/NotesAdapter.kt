package com.example.clase10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clase10.databinding.ItemNoteBinding

class NotesAdapter(private var items: List<NoteModel>) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = items[position]
        holder.binding.tvNoteTitle.text = note.title
        holder.binding.tvNoteContent.text = note.content
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<NoteModel>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
