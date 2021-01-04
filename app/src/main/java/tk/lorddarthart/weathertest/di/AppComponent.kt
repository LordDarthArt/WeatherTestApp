package tk.lorddarthart.weathertest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import tk.lorddarthart.domain.di.FirebaseModule
import tk.lorddarthart.domain.di.NetworkModule
import tk.lorddarthart.domain.di.RepositoryModule
import tk.lorddarthart.utils.di.UtilModule
import tk.lorddarthart.weathertest.WeatherTestApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        UtilModule::class,
        ActivitiesModule::class,
        FragmentsModule::class,
        RepositoryModule::class,
        FirebaseModule::class,
        NetworkModule::class
    ]
)
interface AppComponent: AndroidInjector<WeatherTestApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}