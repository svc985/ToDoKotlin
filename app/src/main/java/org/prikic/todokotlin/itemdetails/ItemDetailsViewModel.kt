package org.prikic.todokotlin.itemdetails

import android.arch.lifecycle.ViewModel
import org.prikic.todokotlin.App
import org.prikic.todokotlin.data.model.ToDo
import org.prikic.todokotlin.data.repository.ToDoRepository
import javax.inject.Inject

class ItemDetailsViewModel: ViewModel() {

    @Inject
    lateinit var toDoRepository: ToDoRepository

    fun saveToDo(toDo: ToDo) {
        App.component.inject(this)

        toDoRepository.saveToDo(toDo)
    }

}