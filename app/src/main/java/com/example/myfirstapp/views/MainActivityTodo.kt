package com.example.myfirstapp.views

import android.content.Intent
import android.graphics.ColorSpace.Model
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.model.TodoModel
import com.example.myfirstapp.utils.DataLayerSingleton
import com.example.myfirstapp.views.todo_recycler_view.TodoListAdapter
import okhttp3.internal.notifyAll
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivityTodo: AppCompatActivity(), TodoOnClickLListener {

    // Views
    private lateinit var todoListRecyclerView: RecyclerView

    // Data
    private val dataLayer = DataLayerSingleton
    private lateinit var todoAdapter: TodoListAdapter
    companion object {
        const val TODO_MODEL_EXTRA = "TODO_MODEL_EXTRA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (!this.dataLayer.getTodoViewModel().todoListLoadedOnce) {
            this.observeTodoListData()
            this.fetchTodoList()
        } else {
            val todoModels = this.dataLayer.getTodoViewModel().todos.value?.toList()
            this.setUpActivityViews(todoModels ?: listOf())
        }
    }

    private fun setUpActivityViews(data: List<TodoModel>) {
        this.todoListRecyclerView = findViewById(R.id.todo_list_recycler_view)

        // Setup RV Adapter
        todoAdapter = TodoListAdapter(data, this)

        // Setup Linear layout manager
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation  = LinearLayoutManager.VERTICAL

        this.todoListRecyclerView.layoutManager = linearLayoutManager
        this.todoListRecyclerView.setAdapter(todoAdapter)
    }


    // Data fetch and observing
    private fun fetchTodoList() {
        // viewModel.getTodo()
        this.dataLayer.getTodoViewModel().fetchTodoFromRepo()
        this.dataLayer.getTodoViewModel().todoListLoadedOnce = true
    }


    private fun observeTodoListData() {
        this.dataLayer.getTodoViewModel().todos.observe(this) { todoList ->
            this.setUpActivityViews(todoList)
        }
    }

    override fun displayTodoDetail(todo: TodoModel) {
        Intent(this, TodoDetailActivity::class.java).also {
            it.putExtra(TODO_MODEL_EXTRA, todo)

            startActivity(it)
        }
    }
}

interface TodoOnClickLListener {
    fun displayTodoDetail(todo: TodoModel)
}