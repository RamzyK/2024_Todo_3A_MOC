package com.example.myfirstapp.data

import com.example.myfirstapp.network.TodoServices
import com.example.myfirstapp.network.TodosRepository
import com.example.myfirstapp.viewmodels.TodoViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataLayerSingleton {

    private lateinit var retrofitClient: Retrofit
    private lateinit var todoService: TodoServices
    private lateinit var todoViewModel: TodoViewModel

    init {
        createRetrofitClient()
        createTodoService()
        initViewModel()
    }

    fun getTodoViewModel() = todoViewModel

    // Setup HTTP client + services
    private fun createRetrofitClient() {
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        retrofitClient = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(gsonConverter)
            .build()
    }

    private fun createTodoService() {
        todoService = retrofitClient.create(TodoServices::class.java)
    }

    private fun initViewModel() {
        todoViewModel = TodoViewModel(
            TodosRepository(todoService)
        )
    }
}