package org.prikic.todokotlin.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.db.TaskDao
import org.prikic.todokotlin.util.Message
import timber.log.Timber
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    private val compositeDisposable = CompositeDisposable()

    fun saveTask(task: Task): LiveData<Message> {

        val data: MutableLiveData<Message> = MutableLiveData()

        compositeDisposable.add(Observable.fromCallable {
            taskDao.insertTask(task)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    Timber.d("success")
                    data.value = Message.SUCCESS
                }
                .doOnError {
                    Timber.e("error")
                    data.value = Message.ERROR
                }
                .subscribe())

        return data
    }

    fun getTasks(): LiveData<List<Task>> = taskDao.getAllTasks()

    fun deleteTask(task: Task): LiveData<Message> {

        val data: MutableLiveData<Message> = MutableLiveData()

        compositeDisposable.add(Observable.fromCallable {
            taskDao.deleteTask(task)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    Timber.d("success")
                    data.value = Message.SUCCESS
                }
                .doOnError {
                    Timber.e("error")
                    data.value = Message.ERROR
                }
                .subscribe())

        return data
    }

    fun updateTask(task: Task) {
        compositeDisposable.add(Observable.fromCallable {
            taskDao.updateTask(task)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    Timber.d("success updating task")
                }
                .doOnError {
                    Timber.e("error updating task")
                }
                .subscribe())
    }
}