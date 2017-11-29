package org.prikic.todokotlin.itemdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import org.prikic.todokotlin.App
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.TaskRepository
import org.prikic.todokotlin.util.Message
import javax.inject.Inject

class ItemDetailsViewModel: ViewModel() {

    @Inject
    lateinit var taskRepository: TaskRepository

    init {
        App.component.inject(this)
    }

    fun saveTask(task: Task) : LiveData<Message> = taskRepository.saveTask(task)

}