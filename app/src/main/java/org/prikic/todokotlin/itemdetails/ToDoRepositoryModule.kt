package org.prikic.todokotlin.itemdetails

import dagger.Module
import dagger.Provides
import org.prikic.todokotlin.data.repository.ToDoRepository
import javax.inject.Singleton

@Module
class ToDoRepositoryModule {
    @Provides
    @Singleton
    fun provideToDoRepositoryModule(): ToDoRepository = ToDoRepository()
}