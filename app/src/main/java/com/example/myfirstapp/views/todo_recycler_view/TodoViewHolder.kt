package com.example.myfirstapp.views.todo_recycler_view

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.model.TodoModel

// Class representing the object linked to the XML
// of on cell of the RV
class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var todoTitle: TextView
    private var todoDate: TextView
    private var todoCheckBox: CheckBox

    init {
        this.todoTitle = itemView.findViewById(R.id.todo_title_text_view)
        this.todoDate = itemView.findViewById(R.id.todo_date_textview)
        this.todoCheckBox = itemView.findViewById(R.id.todo_check_state_check_box)
    }

    fun bind(todoModel: TodoModel) {
        this.todoTitle.text = todoModel.title
        this.todoDate.text = todoModel.date
        this.todoCheckBox.isChecked = todoModel.isChecked
    }
}