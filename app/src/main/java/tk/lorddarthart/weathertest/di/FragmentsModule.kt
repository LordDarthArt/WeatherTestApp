package tk.lorddarthart.weathertest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.lorddarthart.presenter.di.PresenterModule
import tk.lorddarthart.weathertest.view.fragment.account.AccountFragment
import tk.lorddarthart.weathertest.view.fragment.auth.AuthFragment
import tk.lorddarthart.weathertest.view.fragment.citieslist.CitiesListFragment
import tk.lorddarthart.weathertest.view.fragment.citieslist.extended.ExtendedInfoFragment
import tk.lorddarthart.weathertest.view.fragment.pages.general.ExtendedFragmentGeneral
import tk.lorddarthart.weathertest.view.fragment.pages.hourly.ExtendedFragmentHourly

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesAccountFragment(): AccountFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesAuthFragment(): AuthFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesCitiesListFragment(): CitiesListFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesExtendedInfoFragment(): ExtendedInfoFragment

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesExtendedGeneral(): ExtendedFragmentGeneral

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun contributesExtendedHourly(): ExtendedFragmentHourly
}