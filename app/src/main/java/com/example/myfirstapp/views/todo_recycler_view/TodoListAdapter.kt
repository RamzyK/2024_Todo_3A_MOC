package com.example.myfirstapp.views.todo_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.model.TodoModel
import com.example.myfirstapp.views.TodoOnClickLListener

class TodoListAdapter(
    var todoList: List<TodoModel>,
    private val todoClickHandler: TodoOnClickLListener
): RecyclerView.Adapter<TodoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val todoView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_cell_layout, parent, false)
        return TodoViewHolder(todoView)
    }

    override fun getItemCount(): Int {
        return this.todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodoData = this.todoList[position] // Get the data at the right position
        holder.bind(currentTodoData)
        holder.itemView.setOnClickListener {
            todoClickHandler.displayTodoDetail(currentTodoData)
        }
    }
}