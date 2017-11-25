package org.prikic.todokotlin

import android.app.Application
import org.prikic.todokotlin.itemdetails.TaskRepositoryModule
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