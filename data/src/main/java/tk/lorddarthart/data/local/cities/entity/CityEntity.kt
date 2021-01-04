package tk.lorddarthart.data.local.cities.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by LordDarthArt on 23.10.2019.
 */
@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey @ColumnInfo(name = "city_id") val id: Long,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "last_sync") val lastSync: Long
)