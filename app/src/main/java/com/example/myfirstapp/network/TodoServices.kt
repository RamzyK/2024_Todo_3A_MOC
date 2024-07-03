package com.example.myfirstapp.network

import com.example.myfirstapp.model.TodoDto
import retrofit2.Call
import retrofit2.http.GET


interface TodoServices {

    @GET("RamzyK/demo/todos")
    fun getTodos(): Call<List<TodoDto>>
}