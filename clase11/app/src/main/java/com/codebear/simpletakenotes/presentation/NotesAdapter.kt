package com.codebear.simpletakenotes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codebear.simpletakenotes.domain.models.NoteModel
import com.codebear.simpletakenotes.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(
    private var items: List<NoteModel>,
    private val onTap: (NoteModel) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = items[position]
        holder.binding.apply {
            tvTitle.text = note.title
            tvContent.text = note.content
            tvDate.text = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date(note.createdAt))
            root.setOnClickListener { onTap(note) }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<NoteModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}
