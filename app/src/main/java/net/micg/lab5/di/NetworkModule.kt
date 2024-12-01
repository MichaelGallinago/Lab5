package net.micg.lab5.di

import dagger.Module
import dagger.Provides
import net.micg.lab5.BuildConfig
import net.micg.lab5.data.LampApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    @AppComponentScope
    fun provideArticlesApi(): LampApi = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(LampApi::class.java)
}
