package tk.lorddarthart.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.reactivex.Observable
import tk.lorddarthart.data.entities.CityEntity

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    fun getListOfCities(): Observable<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE city_id = :id")
    fun getCityById(id: Int): Observable<CityEntity>

    @Query("SELECT COUNT(*) FROM cities")
    fun recordsCount(): Long

    @Insert(onConflict = IGNORE)
    fun addCity(city: CityEntity): Long

    @Delete
    fun removeCity(city: CityEntity)
}