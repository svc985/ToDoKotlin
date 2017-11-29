package org.prikic.todokotlin.data.repository.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import org.prikic.todokotlin.data.model.Task

@Dao
interface TaskDao {

    @Query("select * from task")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("select * from task where id = :id")
    fun findTaskById(id: Long): Task

    @Insert(onConflict = REPLACE)
    fun insertTask(task: Task)

    @Update(onConflict = REPLACE)
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}