package org.prikic.todokotlin

import android.app.Application
import org.prikic.todokotlin.di.AppComponent
import org.prikic.todokotlin.di.AppModule
import org.prikic.todokotlin.di.DaggerAppComponent
import org.prikic.todokotlin.di.TaskRepositoryModule
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
                .taskRepositoryModule((TaskRepositoryModule()))
                .build()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}