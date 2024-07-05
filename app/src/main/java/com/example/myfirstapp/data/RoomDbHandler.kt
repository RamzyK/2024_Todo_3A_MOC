package com.example.myfirstapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object RoomDbHandler {

    fun getRoomDb(context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "todos-db"
        ).build()
    }
}