package org.prikic.todokotlin

import android.app.Application
import org.prikic.todokotlin.itemdetails.ToDoRepositoryModule
import timber.log.Timber

class App: Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .toDoRepositoryModule((ToDoRepositoryModule()))
                .build()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}