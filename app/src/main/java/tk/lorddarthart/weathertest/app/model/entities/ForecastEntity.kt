package tk.lorddarthart.weathertest.app.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.CITY_FORECAST
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_CITY
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_CLOUDS
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_DATE
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_DESCRIPTION
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_HIGH
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_HUMIDITY
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_ICON
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_ID
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_LAT
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_LON
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_LOW
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_NOW
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_PRESSURE
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_SUNRISE
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_SUNSET
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_TIMEZONE
import tk.lorddarthart.weathertest.util.constant.DatabaseConstantNames.ForecastDatabase.WEATHER_WINDSPEED

/**
 * Created by LordDarthArt on 23.10.2019.
 */
@Entity(tableName = CITY_FORECAST)
data class ForecastEntity(
        @PrimaryKey @ColumnInfo(name = WEATHER_ID) val id: Long,
        @ColumnInfo(name = WEATHER_LAT) val weatherLat: Double,
        @ColumnInfo(name = WEATHER_LON) val weatherLon: Double,
        @ColumnInfo(name = WEATHER_NOW) val weatherNow: Double,
        @ColumnInfo(name = WEATHER_DATE) val weatherDate: Long,
        @ColumnInfo(name = WEATHER_CITY) val weatherCity: String,
        @ColumnInfo(name = WEATHER_HIGH) val weatherHigh: Double,
        @ColumnInfo(name = WEATHER_LOW) val weatherLow: Double,
        @ColumnInfo(name = WEATHER_DESCRIPTION) val weatherDescription: String,
        @ColumnInfo(name = WEATHER_HUMIDITY) val weatherHumidity: Int,
        @ColumnInfo(name = WEATHER_PRESSURE) val weatherPressure: Int,
        @ColumnInfo(name = WEATHER_ICON) val weatherIcon: String,
        @ColumnInfo(name = WEATHER_WINDSPEED) val weatherWindSpeed: Double,
        @ColumnInfo(name = WEATHER_CLOUDS) val weatherClouds: Int,
        @ColumnInfo(name = WEATHER_TIMEZONE) val weatherTimeZone: Long,
        @ColumnInfo(name = WEATHER_SUNRISE) val weatherSunrise: Long,
        @ColumnInfo(name = WEATHER_SUNSET) val weatherSunset: Long
)