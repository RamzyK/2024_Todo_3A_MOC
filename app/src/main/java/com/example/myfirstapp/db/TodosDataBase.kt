package com.example.myfirstapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfirstapp.db.daos.TodoDao
import com.example.myfirstapp.db.entities.TodoEntity


@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class TodosDataBase: RoomDatabase() {
    abstract fun todosDao(): TodoDao
}