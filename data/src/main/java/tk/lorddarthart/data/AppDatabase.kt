package tk.lorddarthart.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tk.lorddarthart.data.dao.CityDao
import tk.lorddarthart.data.dao.ForecastDao
import tk.lorddarthart.data.entities.CityEntity
import tk.lorddarthart.data.entities.ForecastEntity

private const val DATABASE_VERSION = 3

@Database(entities = [CityEntity::class, ForecastEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun forecastDao(): ForecastDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }

        private const val DATABASE_NAME = "weather_forecasts.db"
    }
}