package tk.lorddarthart.weathertest.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tk.lorddarthart.data.AppDatabase
import tk.lorddarthart.data.dao.CityDao
import tk.lorddarthart.data.dao.ForecastDao
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {
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