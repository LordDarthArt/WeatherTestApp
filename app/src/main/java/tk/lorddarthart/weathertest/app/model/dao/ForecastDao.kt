package tk.lorddarthart.weathertest.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Flowable
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.CITY_FORECAST
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_ID

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@Dao
interface ForecastDao {

    @Query("SELECT * FROM $CITY_FORECAST")
    fun getListOfForecasts(): Flowable<List<ForecastEntity>>

    @Query("SELECT * FROM $CITY_FORECAST WHERE $WEATHER_ID = :id")
    fun getForecastDataById(id: Int): Flowable<ForecastEntity>

    @Insert(onConflict = REPLACE)
    fun insertForecast(forecastEntity: ForecastEntity): Long

    @Delete
    fun deleteForecast(forecastEntity: ForecastEntity)
}