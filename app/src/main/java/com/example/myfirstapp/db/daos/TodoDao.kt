package com.example.myfirstapp.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapp.db.entities.TodoEntity

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): List<TodoEntity>

    @Query("SELECT * FROM TODOS WHERE id = :todoId")
    fun getTodoById(todoId: Int): TodoEntity

    @Insert
    fun addTodos(todos: List<TodoEntity>)

    @Delete
    fun removeTodo(todo: TodoEntity)
}