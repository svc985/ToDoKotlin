package org.prikic.todokotlin.di

import dagger.Module
import dagger.Provides
import org.prikic.todokotlin.App
import org.prikic.todokotlin.data.repository.db.TaskDatabase
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun providesAppDatabase(app: App): TaskDatabase = TaskDatabase.buildRoomDb(app)

    @Provides
    fun providesTaskDao(database: TaskDatabase) = database.taskDao()
}