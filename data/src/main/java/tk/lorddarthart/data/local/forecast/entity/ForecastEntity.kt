package tk.lorddarthart.data.local.forecast.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by LordDarthArt on 23.10.2019.
 */
@Entity(tableName = "city_forecast")
data class ForecastEntity(
    @PrimaryKey @ColumnInfo(name = "weather_id") val id: Long,
    @ColumnInfo(name = "weather_lat") val weatherLat: Double,
    @ColumnInfo(name = "weather_lon") val weatherLon: Double,
    @ColumnInfo(name = "weather_now") val weatherNow: Double,
    @ColumnInfo(name = "weather_date") val weatherDate: Long,
    @ColumnInfo(name = "weather_city") val weatherCity: String,
    @ColumnInfo(name = "weather_high") val weatherHigh: Double,
    @ColumnInfo(name = "weather_low") val weatherLow: Double,
    @ColumnInfo(name = "weather_description") val weatherDescription: String,
    @ColumnInfo(name = "weather_humidity") val weatherHumidity: Int,
    @ColumnInfo(name = "weather_pressure") val weatherPressure: Int,
    @ColumnInfo(name = "weather_icon") val weatherIcon: String,
    @ColumnInfo(name = "weather_windspeed") val weatherWindSpeed: Double,
    @ColumnInfo(name = "weather_clouds") val weatherClouds: Int,
    @ColumnInfo(name = "weather_timezone") val weatherTimeZone: Long,
    @ColumnInfo(name = "weather_sunrise") val weatherSunrise: Long,
    @ColumnInfo(name = "weather_sunset") val weatherSunset: Long
)