package com.vangelnum.room.data.network

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vangelnum.room.data.model.TodoItem


@Dao
interface TodoDao {
    @Query("SELECT * from my_todo_list")
    fun getAllNotes(): LiveData<List<TodoItem>>

    @Query("SELECT * from my_todo_list where itemId = :id")
    fun getById(id: Int): TodoItem?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TodoItem)

    @Update
    suspend fun update(item: TodoItem)

    @Delete
    suspend fun delete(item: TodoItem)

    @Query("DELETE FROM my_todo_list")
    suspend fun deleteAllTodos()

}