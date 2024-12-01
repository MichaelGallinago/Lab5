package net.micg.lab5.di

import dagger.Module
import net.micg.lab5.di.NetworkModule
import net.micg.lab5.di.AppBindsModule
import net.micg.lab5.di.viewModel.ViewModelModule

@Module(
    includes = [
        AppBindsModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
class AppModule
