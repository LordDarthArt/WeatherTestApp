package tk.lorddarthart.weathertest.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import tk.lorddarthart.domain.citieslist.CitiesListRepository
import tk.lorddarthart.domain.citieslist.CitiesListRepositoryImpl
import tk.lorddarthart.domain.network.forecast.ForecastApi
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCitiesListRepository(forecastApi: ForecastApi, application: Application): CitiesListRepository = CitiesListRepositoryImpl(forecastApi, application.applicationContext)
}