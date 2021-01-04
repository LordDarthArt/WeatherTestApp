package tk.lorddarthart.domain.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tk.lorddarthart.domain.AppDatabase
import tk.lorddarthart.domain.dao.CityDao
import tk.lorddarthart.domain.dao.ForecastDao
import javax.inject.Singleton

@Module
class DataModule(application: Application) {
    private val appDatabase: AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "weather_forecasts.db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesCityDao(appDatabase: AppDatabase): CityDao {
        return appDatabase.cityDao()
    }

    @Singleton
    @Provides
    fun providesForecastDao(appDatabase: AppDatabase): ForecastDao {
        return appDatabase.forecastDao()
    }
}