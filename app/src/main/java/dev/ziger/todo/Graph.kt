package dev.ziger.todo

import android.content.Context
import dev.ziger.todo.data.TodoDataSource
import dev.ziger.todo.data.room.TodoDatabase

object Graph {
    lateinit var database: TodoDatabase
        private set

    val todoRepo by lazy {
        TodoDataSource(database.todoDao())
    }

    fun provide(context: Context) {
        database = TodoDatabase.getDatabase(context)
    }
}