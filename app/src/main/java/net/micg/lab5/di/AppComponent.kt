package net.micg.lab5.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import net.micg.lab5.presenter.MainFragment

@AppComponentScope
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: MainFragment)
}
