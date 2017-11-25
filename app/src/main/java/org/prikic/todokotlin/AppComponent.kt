package org.prikic.todokotlin

import dagger.Component
import org.prikic.todokotlin.data.repository.TaskRepository
import org.prikic.todokotlin.itemdetails.ItemDetailsViewModel
import org.prikic.todokotlin.itemdetails.TaskRepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, TaskRepositoryModule::class))
interface AppComponent {
    fun inject(app: App)
    fun inject(itemDetailsViewModel: ItemDetailsViewModel)
    fun inject(taskRepository: TaskRepository)
}