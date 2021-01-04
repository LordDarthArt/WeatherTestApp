package tk.lorddarthart.weathertest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.lorddarthart.presenter.di.PresenterModule
import tk.lorddarthart.weathertest.view.activity.MainActivity

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesMainActivity(): MainActivity
}