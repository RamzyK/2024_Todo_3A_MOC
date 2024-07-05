package com.example.myfirstapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "todo_title")
    val title: String,
    @ColumnInfo(name = "todo_description")
    val description: String,
    @ColumnInfo(name = "todo_date")
    val date: String,
    @ColumnInfo(name = "todo_is_completed")
    val isChecked: Boolean
)
