package org.prikic.todokotlin.data.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.prikic.todokotlin.data.model.Task

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    companion object {
        fun buildRoomDb(context: Context): TaskDatabase = Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "task_db"
        ).build()
    }

    abstract fun taskDao(): TaskDao

}