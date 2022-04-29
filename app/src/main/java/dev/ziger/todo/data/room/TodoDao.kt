package dev.ziger.todo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ziger.todo.data.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun selectAllTodo(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodo(id: Long)

    @Query("DELETE FROM todo")
    suspend fun deleteAllTodo()

    @Query("UPDATE todo SET isComplete = :isComplete WHERE id = :id")
    suspend fun updateTodo(isComplete:Boolean, id:Long)
}