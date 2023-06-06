package com.vangelnum.room.domain.repository

import androidx.lifecycle.LiveData
import com.vangelnum.room.data.model.TodoItem

interface TodoRepository {

    fun getAllNotes(): LiveData<List<TodoItem>>

    suspend fun addTodo(todoItem: TodoItem)

    suspend fun updateTodo(todoItem: TodoItem)

    suspend fun deleteTodo(todoItem: TodoItem)

    suspend fun deleteAllTodos()

}