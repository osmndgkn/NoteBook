package com.example.notebook.repository


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.notebook.data.NoteDao
import com.example.notebook.model.note

class NoteRepository(private val noteDao: NoteDao) {

    val readNotes: LiveData<List<note>> = noteDao.getAll()

    suspend fun addNote(note: note){
        noteDao.insertAll(note)
    }

   suspend fun updateNote(note: note) {
        Log.d("TAG", "updateNote: $note")
        Log.d("TAG", "updateNote: $readNotes")
        noteDao.update(note)
    }

    fun deleteNote(note: note){
        noteDao.delete(note)
    }


}