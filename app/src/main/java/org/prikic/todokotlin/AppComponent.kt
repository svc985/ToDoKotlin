package org.prikic.todokotlin

import dagger.Component
import org.prikic.todokotlin.itemdetails.ItemDetailsViewModel
import org.prikic.todokotlin.itemdetails.ToDoRepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ToDoRepositoryModule::class))
interface AppComponent {
    fun inject(app: App)
    fun inject(itemDetailsViewModel: ItemDetailsViewModel)
}