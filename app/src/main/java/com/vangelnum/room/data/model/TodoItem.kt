package com.vangelnum.room.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_todo_list")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "subtitle")
    val subtitle: String,

    @ColumnInfo(name = "time")
    var time: String,

    @ColumnInfo(name = "color")
    var color: String,
)