package tk.lorddarthart.weathertest.app.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.CityDatabase.CITIES
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.CityDatabase.CITY_ID
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.CityDatabase.CITY_NAME
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.CityDatabase.LAST_SYNC

/**
 * Created by LordDarthArt on 23.10.2019.
 */
@Entity(tableName = CITIES)
data class CityEntity(
        @PrimaryKey @ColumnInfo(name = CITY_ID) val id: Long,
        @ColumnInfo(name = CITY_NAME) val cityName: String,
        @ColumnInfo(name = LAST_SYNC) val lastSync: Long
) {

}