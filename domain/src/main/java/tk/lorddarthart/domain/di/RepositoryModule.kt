package tk.lorddarthart.domain.di

import android.app.Application
import dagger.Module
import dagger.Provides
import tk.lorddarthart.domain.repository.citieslist.CitiesListRepository
import tk.lorddarthart.domain.repository.citieslist.CitiesListRepositoryImpl
import tk.lorddarthart.domain.api.forecast.ForecastApi
import tk.lorddarthart.domain.api.translator.TranslatorApi
import tk.lorddarthart.domain.repository.translator.TranslatorRepository
import tk.lorddarthart.domain.repository.translator.TranslatorRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCitiesListRepository(forecastApi: ForecastApi, application: Application): CitiesListRepository = CitiesListRepositoryImpl(forecastApi, application.applicationContext)

    @Singleton
    @Provides
    fun provideTranslatorRepository(translatorApi: TranslatorApi): TranslatorRepository = TranslatorRepositoryImpl(translatorApi)
}