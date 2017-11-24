package org.prikic.todokotlin.data.repository

import org.prikic.todokotlin.data.model.ToDo
import timber.log.Timber


class ToDoRepository {

    fun saveToDo(toDo: ToDo) {
        Timber.d("save to db:$toDo")
    }
}