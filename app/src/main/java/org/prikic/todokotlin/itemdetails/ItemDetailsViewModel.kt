package org.prikic.todokotlin.itemdetails

import android.arch.lifecycle.ViewModel
import org.prikic.todokotlin.App
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.TaskRepository
import javax.inject.Inject

class ItemDetailsViewModel: ViewModel() {

    @Inject
    lateinit var taskRepository: TaskRepository

    fun saveToDo(task: Task) {
        App.component.inject(this)

        taskRepository.saveTask(task)
    }

}