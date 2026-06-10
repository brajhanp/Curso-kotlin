package com.example.clase6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clase6.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private var puedeMostrarLista = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.fabAddNote.setOnClickListener {
            puedeMostrarLista = !puedeMostrarLista
            actualizarInterfaz()
        }
    }

    private fun setupRecyclerView() {
        val dummyNotes = listOf(
            Note("Lista de compras", "Comprar leche, pan, huevos y frutas.", "12/12/2026"),
            Note("Ideas de proyecto", "Crear una app de notas con sincronización en la nube.", "12/12/2026"),
            Note("Recordatorio", "Llamar al médico para agendar cita.", "12/12/2026"),
            Note("Tareas del día", "Terminar el informe, enviar correos y revisar pendientes.", "12/12/2026"),
            Note("Frase motivacional", "La constancia vence lo que la dicha no alcanza.", "12/12/2026")
        )

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = NoteAdapter(dummyNotes)
    }

    private fun actualizarInterfaz() {
        if (puedeMostrarLista) {
            binding.rvNotes.visibility = View.VISIBLE
            binding.llEmptyMessageState.visibility = View.GONE
        } else {
            binding.rvNotes.visibility = View.GONE
            binding.llEmptyMessageState.visibility = View.VISIBLE
        }
    }
}
