package org.prikic.todokotlin.data.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.db.TaskDao
import timber.log.Timber
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    private val compositeDisposable = CompositeDisposable()

    fun saveTask(task: Task) {

        compositeDisposable.add(Observable.fromCallable {
            taskDao.insertTask(task)
        }.doOnComplete {
            Timber.d("success")
        }.doOnError {
            Timber.e("error")
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())


    }
}