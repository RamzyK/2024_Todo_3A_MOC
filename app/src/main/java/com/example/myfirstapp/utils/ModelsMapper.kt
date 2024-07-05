package com.example.myfirstapp.utils

import com.example.myfirstapp.db.entities.TodoEntity
import com.example.myfirstapp.model.TodoModel

fun mapTodoModelToEntity(model: TodoModel): TodoEntity {
    return TodoEntity(
        title = model.title ?: "TODO",
        description = model.description ?: "---",
        date = model.date ?: "-- / -- / --",
        isChecked = model.isChecked
    )
}

fun mapTodoEntityToModel(entity: TodoEntity): TodoModel {
    return TodoModel(
        title = entity.title,
        description = entity.description,
        date = entity.date,
        isChecked = entity.isChecked
    )
}