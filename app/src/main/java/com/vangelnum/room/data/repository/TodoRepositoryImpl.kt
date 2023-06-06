package com.vangelnum.room.data.repository

import androidx.lifecycle.LiveData
import com.vangelnum.room.data.model.TodoItem
import com.vangelnum.room.data.network.TodoDao
import com.vangelnum.room.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDatabaseDao: TodoDao
) : TodoRepository {

    override fun getAllNotes(): LiveData<List<TodoItem>> {
        return todoDatabaseDao.getAllNotes()
    }

    override suspend fun addTodo(todoItem: TodoItem) {
        todoDatabaseDao.insert(todoItem)
    }

    override suspend fun updateTodo(todoItem: TodoItem) {
        todoDatabaseDao.update(todoItem)
    }

    override suspend fun deleteTodo(todoItem: TodoItem) {
        todoDatabaseDao.delete(todoItem)
    }

    override suspend fun deleteAllTodos() {
        todoDatabaseDao.deleteAllTodos()
    }
}