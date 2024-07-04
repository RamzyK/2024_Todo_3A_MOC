package com.example.myfirstapp.views

import android.content.Intent
import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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


class MainActivityTodo: AppCompatActivity(), TodoOnClickLListener, CalendarDateHandler {

    // Views
    private lateinit var todoListRecyclerView: RecyclerView

    // Data
    private val dataLayer = DataLayerSingleton
    private lateinit var todoAdapter: TodoListAdapter

    private lateinit var calendarFragmentView: CalendarFragmentView
    companion object {
        const val TODO_MODEL_EXTRA = "TODO_MODEL_EXTRA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        this.title = "Todos"
        // Init calendar fragment
        this.calendarFragmentView = supportFragmentManager.findFragmentByTag("CALENDAR_VIEW") as CalendarFragmentView
        this.calendarFragmentView.setUpCalendarHandler(this)

        if (!this.dataLayer.getTodoViewModel().todoListLoadedOnce) {
            this.observeTodoListData()
            this.fetchTodoList()
        } else {
            val todoModels = this.dataLayer.getTodoViewModel().todos.value?.toList()
            this.setUpActivityViews(todoModels ?: listOf())
        }
    }


    // Calendar
    override fun getSelectedDate(day: Int, month: Int, year: Int) {
        val formattedDate = "$year-$month-$day"
        Log.d("Selected date", formattedDate)
        val todosFilteredByDate = dataLayer.getTodoViewModel().todos.value?.filter { it.date == formattedDate } ?: listOf()
        this.todoAdapter.todoList = todosFilteredByDate
        this.todoAdapter.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.todos_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.reload_data_menu_action_button) {
            this.calendarFragmentView.setUpCalendarView()
            this.todoAdapter.todoList = dataLayer.getTodoViewModel().todos.value ?: listOf()
            this.todoAdapter.notifyDataSetChanged()
        }
        return super.onOptionsItemSelected(item)
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
            // Filter todos for today
            val todayTodos = todoList
            this.setUpActivityViews(todayTodos)
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

interface CalendarDateHandler {
    fun getSelectedDate(day: Int, month: Int, year: Int)
}