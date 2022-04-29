package dev.ziger.todo.data

import dev.ziger.todo.data.room.TodoDao
import kotlinx.coroutines.Dispatchers

class TodoDataSource(private val todoDao: TodoDao) {
    val selectAll = todoDao.selectAllTodo()

    suspend fun insertTodo(todo: Todo) {
        Dispatchers.IO.apply {
            todoDao.insertTodo(todo)
        }
    }

    suspend fun deleteTodo(todo: Todo) {
        Dispatchers.IO.apply {
            todoDao.deleteTodo(todo.id)
        }
    }

    suspend fun updateTodo(isCompleted:Boolean, id:Long) {
        Dispatchers.IO.apply {
            todoDao.updateTodo(isCompleted, id)
        }
    }
}