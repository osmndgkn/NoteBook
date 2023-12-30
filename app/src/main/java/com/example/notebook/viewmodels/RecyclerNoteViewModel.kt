package com.example.notebook.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


import com.example.notebook.data.AppDatabase
import com.example.notebook.model.note
import com.example.notebook.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecyclerNoteViewModel(application: Application) : AndroidViewModel(application) {

     val notes : LiveData<List<note>>
    private val repository : NoteRepository


init {
    val noteDao  = AppDatabase.getDatabase(application).noteDao()
    repository = NoteRepository(noteDao)
    notes = repository.readNotes
}

    fun addNote(note: note){

    GlobalScope.launch(Dispatchers.IO) { repository.addNote(note)  }
    }

    fun updateNote(note : note){
        Log.d("TAG", "updateNote:  $note")
        GlobalScope.launch (Dispatchers.IO ){ repository.updateNote(note) }
    }

    fun deleteNote(note: note){
        GlobalScope.launch (Dispatchers.IO ){ repository.deleteNote(note) }
    }


}