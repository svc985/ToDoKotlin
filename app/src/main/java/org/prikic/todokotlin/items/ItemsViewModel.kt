package org.prikic.todokotlin.items

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import org.prikic.todokotlin.App
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.TaskRepository
import org.prikic.todokotlin.util.Message
import timber.log.Timber
import javax.inject.Inject

class ItemsViewModel: ViewModel() {

    @Inject
    lateinit var taskRepository: TaskRepository

    init {
        App.component.inject(this)
    }

    fun getTasks() : LiveData<List<Task>> = taskRepository.getTasks()

    fun deleteTask(task: Task?) : LiveData<Message>{
        Timber.d("delete task:$task")

        return taskRepository.deleteTask(task!!)
    }
}