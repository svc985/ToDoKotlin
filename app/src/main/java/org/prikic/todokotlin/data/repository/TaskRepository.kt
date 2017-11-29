package org.prikic.todokotlin.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.data.repository.db.TaskDao
import org.prikic.todokotlin.itemdetails.ItemDetailsActivity
import timber.log.Timber
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    private val compositeDisposable = CompositeDisposable()

    fun saveTask(task: Task): LiveData<ItemDetailsActivity.Message> {

        val data: MutableLiveData<ItemDetailsActivity.Message> = MutableLiveData()

        compositeDisposable.add(Observable.fromCallable {
            taskDao.insertTask(task)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    Timber.d("success")
                    data.value = ItemDetailsActivity.Message.SUCCESS
                }
                .doOnError {
                    Timber.e("error")
                    data.value = ItemDetailsActivity.Message.ERROR
                }
                .subscribe())

        return data
    }

    fun getTasks(): LiveData<List<Task>> = taskDao.getAllTasks()
}