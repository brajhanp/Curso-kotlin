package com.codebear.simpletakenotes.data

import androidx.room.*
import com.codebear.simpletakenotes.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)
}
