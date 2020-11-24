package tk.lorddarthart.weathertest.di

import dagger.Module
import dagger.Provides
import tk.lorddarthart.weathertest.app.logic.CitiesListRepository
import tk.lorddarthart.weathertest.app.presenter.activity.MainActivityPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.account.AccountFragmentPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.auth.AuthFragmentPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.cities_list.CitiesListFragmentPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.cities_list.extended.ExtendedInfoFragmentPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.page.general.ExtendedFragmentGeneralPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.page.hourly.ExtendedFragmentHourlyPresenter
import tk.lorddarthart.weathertest.util.network.forecast.ForecastApi

@Module
class PresenterModule {
    @Provides
    fun provideMainActivityPresenter() = MainActivityPresenter()

    @Provides
    fun provideAccountPresenter() = AccountFragmentPresenter()

    @Provides
    fun provideAuthPresenter() = AuthFragmentPresenter()

    @Provides
    fun provideCitiesListPresenter(citiesListRepository: CitiesListRepository) = CitiesListFragmentPresenter(citiesListRepository)

    @Provides
    fun provideExtendedInfoPresenter() = ExtendedInfoFragmentPresenter()

    @Provides
    fun provideExtendedGeneralPresenter() = ExtendedFragmentGeneralPresenter()

    @Provides
    fun provideExtendedHourlyPresenter() = ExtendedFragmentHourlyPresenter()
}