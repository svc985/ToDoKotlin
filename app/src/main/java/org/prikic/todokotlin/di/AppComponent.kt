package org.prikic.todokotlin.di

import dagger.Component
import org.prikic.todokotlin.App
import org.prikic.todokotlin.data.repository.TaskRepository
import org.prikic.todokotlin.itemdetails.ItemDetailsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, TaskRepositoryModule::class))
interface AppComponent {
    fun inject(app: App)
    fun inject(itemDetailsViewModel: ItemDetailsViewModel)
    fun inject(taskRepository: TaskRepository)
}