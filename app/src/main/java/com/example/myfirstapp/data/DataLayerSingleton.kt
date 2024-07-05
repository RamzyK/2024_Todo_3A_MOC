package com.example.myfirstapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstapp.db.TodosDataBase
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
    private lateinit var todoDb: TodosDataBase


    fun getTodoViewModel() = todoViewModel

    fun getRoomDb(context: Context) {
        todoDb = Room.databaseBuilder(
            context,
            TodosDataBase::class.java,
            "todos-db"
        ).build()
    }

    // Setup HTTP client + services
    fun createRetrofitClient() {
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        retrofitClient = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(gsonConverter)
            .build()
    }

    fun createTodoService() {
        todoService = retrofitClient.create(TodoServices::class.java)
    }

    fun initViewModel() {
        todoViewModel = TodoViewModel(
            TodosRepository(
                todoService,
                todoDb
            )
        )
    }
}