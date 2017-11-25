package org.prikic.todokotlin.data.repository

import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.db.TaskDao
import timber.log.Timber
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    fun saveTask(task: Task) {
        if (taskDao == null) {
            Timber.d("taskDao is null")
        } else {
            Timber.d("taskDao is NOT null, yay")
        }

        //taskDao.insertTask(task)
    }
}