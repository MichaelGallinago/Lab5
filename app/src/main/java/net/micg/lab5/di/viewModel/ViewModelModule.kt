package net.micg.lab5.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.micg.lab5.presenter.MainViewModel
import net.micg.lab5.di.AppComponentScope

@Module
interface ViewModelModule {
    @AppComponentScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(vm: MainViewModel): ViewModel
}
