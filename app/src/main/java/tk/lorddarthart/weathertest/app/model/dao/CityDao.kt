package tk.lorddarthart.weathertest.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import tk.lorddarthart.weathertest.app.model.entities.CityEntity
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.CityDatabase.CITIES
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.CityDatabase.CITY_ID

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@Dao
interface CityDao {

    @Query("SELECT * FROM $CITIES")
    fun getListOfCities(): Observable<List<CityEntity>>

    @Query("SELECT * FROM $CITIES WHERE $CITY_ID = :id")
    fun getCityById(id: Int): Observable<CityEntity>

    @Query("SELECT COUNT(*) FROM $CITIES")
    fun recordsCount(): Long

    @Insert(onConflict = IGNORE)
    fun addCity(city: CityEntity): Long

    @Delete
    fun removeCity(city: CityEntity)
}