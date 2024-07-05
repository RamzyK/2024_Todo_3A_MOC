package com.example.myfirstapp.network

import androidx.room.RoomDatabase
import com.example.myfirstapp.model.TodoDto
import retrofit2.Call

class TodosRepository(
    private val todoService: TodoServices,
    private val todoDb: RoomDatabase
) {

    fun fetchTodos(): Call<List<TodoDto>> {
        return todoService.getTodos()
    }
}