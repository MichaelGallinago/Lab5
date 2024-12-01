package net.micg.lab5.di

import android.content.Context
import net.micg.lab5.Lab5Application

val Context.appComponent: AppComponent
    get() = when(this) {
        is Lab5Application -> appComponent
        else -> applicationContext.appComponent
    }
