package com.example.myfirstapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstapp.db.TodosDataBase

object RoomDbHandler {

    fun getRoomDb(context: Context): TodosDataBase {
        return Room.databaseBuilder(
            context,
            TodosDataBase::class.java,
            "todos-db"
        ).build()
    }
}