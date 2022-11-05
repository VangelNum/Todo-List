package com.vangelnum.room

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val text = mutableStateOf("")

    val readAllData: LiveData<List<TodoItem>>
    private val repository: TodoRepository

    init {
        val todoDao = TodoDatabase.getInstance(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todoItem)
        }
    }

    fun updateTodo(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todoItem = todoItem)
        }
    }

    fun deleteTodo(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todoItem = todoItem)
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTodos()
        }
    }
}

