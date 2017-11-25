package org.prikic.todokotlin.itemdetails

import dagger.Module
import dagger.Provides
import org.prikic.todokotlin.data.repository.TaskRepository
import org.prikic.todokotlin.data.repository.db.TaskDao
import javax.inject.Singleton

@Module
class TaskRepositoryModule {
    @Provides
    @Singleton
    fun provideTaskRepositoryModule(taskDao: TaskDao): TaskRepository = TaskRepository(taskDao)
}