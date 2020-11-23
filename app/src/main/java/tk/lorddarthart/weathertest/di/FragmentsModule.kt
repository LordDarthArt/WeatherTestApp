package tk.lorddarthart.weathertest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.lorddarthart.weathertest.app.view.fragment.account.AccountFragment
import tk.lorddarthart.weathertest.app.view.fragment.auth.AuthFragment
import tk.lorddarthart.weathertest.app.view.fragment.main.MainFragment
import tk.lorddarthart.weathertest.app.view.fragment.main.cities_list.CitiesListFragment
import tk.lorddarthart.weathertest.app.view.fragment.main.extended.ExtendedInfoFragment
import tk.lorddarthart.weathertest.app.view.fragment.pages.general.ExtendedFragmentGeneral
import tk.lorddarthart.weathertest.app.view.fragment.pages.hourly.ExtendedFragmentHourly

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesAccountFragment(): AccountFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesAuthFragment(): AuthFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesMainFragment(): MainFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesCitiesListFragment(): CitiesListFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesExtendedInfoFragment(): ExtendedInfoFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesExtendedGeneral(): ExtendedFragmentGeneral

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesExtendedHourly(): ExtendedFragmentHourly
}