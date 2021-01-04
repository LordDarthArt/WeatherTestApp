package tk.lorddarthart.presenter.di

import dagger.Module
import dagger.Provides
import tk.lorddarthart.domain.repository.citieslist.CitiesListRepository
import tk.lorddarthart.presenter.activity.MainActivityPresenter
import tk.lorddarthart.presenter.fragment.account.AccountFragmentPresenter
import tk.lorddarthart.presenter.fragment.auth.AuthFragmentPresenter
import tk.lorddarthart.presenter.fragment.cities_list.CitiesListFragmentPresenter
import tk.lorddarthart.presenter.fragment.cities_list.extended.ExtendedInfoFragmentPresenter
import tk.lorddarthart.presenter.fragment.page.general.ExtendedFragmentGeneralPresenter
import tk.lorddarthart.presenter.fragment.page.hourly.ExtendedFragmentHourlyPresenter

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