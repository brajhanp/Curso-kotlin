package com.example.clase7

class NotesDatabase {
    fun getFakeNotes(): List<NoteModel> {
        return mutableListOf(
            NoteModel(1, "Lista de Compras", "Comprar leche, pan, huevos y frutas.", "20/10/2023"),
            NoteModel(2, "Reunión de Trabajo", "Presentar el reporte trimestral a las 10:00 AM.", "21/10/2023"),
            NoteModel(3, "Gimnasio", "Entrenamiento de pierna y cardio.", "22/10/2023")
        )
    }
}
