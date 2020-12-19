package tk.lorddarthart.weathertest.di

import dagger.Module
import dagger.Provides
import tk.lorddarthart.weathertest.app.logic.citieslist.CitiesListRepository
import tk.lorddarthart.weathertest.app.logic.citieslist.CitiesListRepositoryImpl
import tk.lorddarthart.weathertest.util.network.forecast.ForecastApi
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCitiesListRepository(forecastApi: ForecastApi): CitiesListRepository = CitiesListRepositoryImpl(forecastApi)
}