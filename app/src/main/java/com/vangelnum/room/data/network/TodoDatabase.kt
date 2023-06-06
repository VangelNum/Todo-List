package com.vangelnum.room.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vangelnum.room.data.model.TodoItem

@Database(entities = [TodoItem::class], version = 14)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}