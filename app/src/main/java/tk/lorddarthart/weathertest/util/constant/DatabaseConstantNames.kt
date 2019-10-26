package tk.lorddarthart.weathertest.util.constant

/**
 * Created by LordDarthArt on 26.10.2019.
 */
object DatabaseConstantNames {
    object ForecastDatabase {
        const val CITY_FORECAST = "city_forecast"
        const val WEATHER_ID = "weather_id"
        const val WEATHER_LAT = "weather_lat"
        const val WEATHER_LON = "weather_lon"
        const val WEATHER_NOW = "weather_now"
        const val WEATHER_DATE = "weather_date"
        const val WEATHER_CITY = "weather_city"
        const val WEATHER_HIGH = "weather_high"
        const val WEATHER_LOW = "weather_low"
        const val WEATHER_DESCRIPTION = "weather_description"
        const val WEATHER_HUMIDITY = "weather_humidity"
        const val WEATHER_PRESSURE = "weather_pressure"
        const val WEATHER_ICON = "weather_icon"
        const val WEATHER_WINDSPEED = "weather_wind_speed"
        const val WEATHER_CLOUDS = "weather_clouds"
        const val WEATHER_TIMEZONE = "weather_timezone"
        const val WEATHER_SUNRISE = "weather_sunrise"
        const val WEATHER_SUNSET = "weather_sunset"
    }

    object CityDatabase {
        const val CITIES = "cities"
        const val CITY_ID = "city_id"
        const val CITY_NAME = "city_name"
        const val LAST_SYNC = "last_sync"
    }
}