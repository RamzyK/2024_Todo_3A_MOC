package com.example.myfirstapp.network

import androidx.room.RoomDatabase
import com.example.myfirstapp.db.TodosDataBase
import com.example.myfirstapp.db.entities.TodoEntity
import com.example.myfirstapp.model.TodoDto
import com.example.myfirstapp.model.TodoModel
import com.example.myfirstapp.utils.mapTodoEntityToModel
import com.example.myfirstapp.utils.mapTodoModelToEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class TodosRepository(
    private val todoService: TodoServices,
    private val todoDb: TodosDataBase
) {

    fun fetchTodos(): Call<List<TodoDto>> {
        return todoService.getTodos()
    }

    fun getAllTodosFromDb(): List<TodoModel> {
        return this
            .todoDb
            .todosDao()
            .getAllTodos()
            .map {
            mapTodoEntityToModel(it)
        }
    }

    fun insertTodosInDb(todos: List<TodoModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val mappedTodosEntity = todos.map {
                mapTodoModelToEntity(it)
            }

            this@TodosRepository.todoDb.todosDao().addTodos(mappedTodosEntity)
        }
    }


    fun removeTodoFromDb() {

    }


}