package tk.lorddarthart.weathertest.di

import android.app.Application
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun bindApplication(application: Application): Application
}