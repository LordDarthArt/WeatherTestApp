package tk.lorddarthart.weathertest.util.constant

object Network {
    const val BASE_URL = "https://api.openweathermap.org/" // openweathermap base api url
    const val GET_PREFERRED_LOCATION_WEATHER = "/data/2.5/weather" // openweathermap path to retrieve current forecast for preferred location
    const val GET_LOCATION_HOURLY_WEATHER = "/data/2.5/forecast/hourly" // openweathermap path to retrieve hourly forecast for preferred location
    const val GET_LOCATION_DAILY_WEATHER = "/data/2.5/forecast/daily" // openweathermap path to retrieve daily forecast for preferred location
    const val OPENWEATHERMAP_API_KEY = "1bef5f9781f5652023b95a6fa64dd313" // openweathermap api key to retrieve forecasts

    /**
     *  REST API Field Names for "Current Forecast" Main Server Response body
      */
    const val COORDINATES_FIELD = "coord" // coordinates contains longitude and latitude
    const val WEATHER_FIELD = "weather" // weather contains some additional info on current weather status
    const val BASE_FIELD = "base"
    const val MAIN_FIELD = "main"
    const val VISIBILITY_FIELD = "visibility"
    const val WIND_FIELD = "wind"
    const val RAIN_FIELD = "rain"
    const val CLOUDS_FIELD = "clouds"
    const val DAYTIME_FIELD = "dt"
    const val SYS_FIELD = "sys"
    const val TIMEZONE_FIELD = "timezone"
    const val ID_FIELD = "id"
    const val NAME_FIELD = "name"
    const val COD_FIELD = "cod"

    /**
     * REST API Field Names for "coord" body
      */
    const val COORD_LON_FIELD = "lon"
    const val COORD_LAT_FIELD = "lat"

    /**
     * REST API Field Names for "weather" body
      */
    const val WEATHER_ID_FIELD = ID_FIELD
    const val WEATHER_MAIN_FIELD = MAIN_FIELD
    const val WEATHER_DESCRIPTION_FIELD = "description"
    const val WEATHER_ICON_FIELD = "icon"

    /**
     * REST API Field Names for "main" body
      */
    const val MAIN_TEMP_FIELD = "temp"
    const val MAIN_PRESSURE_FIELD = "pressure"
    const val MAIN_HUMIDITY_FIELD = "humidity"
    const val MAIN_TEMP_MIN_FIELD = "temp_min"
    const val MAIN_TEMP_MAX_FIELD = "temp_max"

    /**
     * REST API Field Names for "wind" body
      */
    const val WIND_SPEED_FIELD = "speed"
    const val WIND_DEG_FIELD = "deg"

    /**
     * REST API Field Names for "rain" body
      */
    const val RAIN_3H_FIELD = "3h"

    /**
     * REST API Field Names for "clouds" body
      */
    const val CLOUDS_ALL_FIELD = "all"

    /**
     * REST API Field Names for "sys" body
      */
    const val SYS_TYPE_FIELD = "type"
    const val SYS_ID_FIELD = ID_FIELD
    const val SYS_MESSAGE_FIELD = "message"
    const val SYS_COUNTRY_FIELD = "country"
    const val SYS_SUNRISE_FIELD = "sunrise"
    const val SYS_SUNSET_FIELD = "sunset"

    const val CITY_FIELD = "city"
    const val CNT_FIELD = "cnt"
    const val LIST_FIELD = "list"
}