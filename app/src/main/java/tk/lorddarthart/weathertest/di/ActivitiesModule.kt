package tk.lorddarthart.weathertest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.lorddarthart.weathertest.app.view.activity.MainActivity

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesMainActivity(): MainActivity
}