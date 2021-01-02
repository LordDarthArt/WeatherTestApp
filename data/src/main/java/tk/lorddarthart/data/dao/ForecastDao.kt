package tk.lorddarthart.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single
import tk.lorddarthart.data.entities.ForecastEntity

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@Dao
interface ForecastDao {

    @Query("SELECT * FROM city_forecast")
    fun getListOfForecasts(): Single<List<ForecastEntity>>

    @Query("SELECT * FROM city_forecast WHERE weather_id = :id")
    fun getForecastDataById(id: Int): Maybe<ForecastEntity>

    @Insert(onConflict = REPLACE)
    fun insertForecast(forecastEntity: ForecastEntity): Long

    @Delete
    fun deleteForecast(forecastEntity: ForecastEntity)
}