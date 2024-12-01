package net.micg.lab5

import android.app.Application
import net.micg.lab5.di.AppComponent
import net.micg.lab5.di.DaggerAppComponent

class Lab5Application: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        super.onCreate()
    }
}
