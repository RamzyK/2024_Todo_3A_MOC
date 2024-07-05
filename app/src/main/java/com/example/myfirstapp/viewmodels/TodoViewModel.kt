package com.example.myfirstapp.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfirstapp.model.TodoDto
import com.example.myfirstapp.model.TodoModel
import com.example.myfirstapp.network.TodosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(private val todoRepository: TodosRepository) {
    var todos: MutableLiveData<ArrayList<TodoModel>> = MutableLiveData()

    var todoListLoadedOnce = false
    suspend fun fetchTodoFromRepo() {
        withContext(Dispatchers.IO) {
            val localTodoData = this@TodoViewModel.todoRepository.getAllTodosFromDb()

            if (localTodoData.isEmpty()) {
                this@TodoViewModel.fetchDataFromApi()
            } else {
                this@TodoViewModel.todos.postValue(ArrayList(localTodoData))
            }
        }
    }

    private fun fetchDataFromApi() {
        val todosApiResponse = this@TodoViewModel.todoRepository.fetchTodos()

        todosApiResponse.enqueue(object : Callback<List<TodoDto>> {
            override fun onFailure(p0: Call<List<TodoDto>>, t: Throwable) {
                Log.d("Error", t.message ?: "Error")
            }

            override fun onResponse(p0: Call<List<TodoDto>>, response: Response<List<TodoDto>>) {
                val responseBody: List<TodoDto> = response.body() ?: listOf()
                val mappedResponse = responseBody.map {
                    TodoModel(
                        it.title,
                        it.description,
                        it.due_date,
                        it.completed
                    )
                }
                this@TodoViewModel.todoRepository.insertTodosInDb(mappedResponse)
                todos.value = ArrayList(mappedResponse)
            }
        })
    }
}