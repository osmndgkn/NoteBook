package com.example.notebook.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notebook.model.note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<note>>

    @Insert
    fun insertAll(note: note)

    @Delete
    fun delete(note: note)

    @Update
    suspend fun update(note: note)





}