package org.prikic.todokotlin.itemdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import org.prikic.todokotlin.App
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.TaskRepository
import javax.inject.Inject

class ItemDetailsViewModel: ViewModel() {

    @Inject
    lateinit var taskRepository: TaskRepository

    fun saveToDo(task: Task) : LiveData<String> {
        App.component.inject(this)

        return taskRepository.saveTask(task)
    }

}