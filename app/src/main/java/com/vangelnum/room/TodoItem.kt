package com.vangelnum.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_todo_list")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "subtitle")
    val subtitle: String,

    @ColumnInfo(name = "time")
    var time: String
)