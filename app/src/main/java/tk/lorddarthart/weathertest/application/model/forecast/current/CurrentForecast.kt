package tk.lorddarthart.weathertest.application.model.forecast.current

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.application.model.forecast.current.sub.*
import tk.lorddarthart.weathertest.util.constant.Network.BASE_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.CLOUDS_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.COD_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.COORDINATES_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.DAYTIME_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.ID_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.MAIN_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.NAME_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.RAIN_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.SYS_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.TIMEZONE_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.VISIBILITY_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.WEATHER_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.WIND_FIELD

class CurrentForecast {
    @SerializedName(COORDINATES_FIELD)
    @Expose
    lateinit var coord: Coord
    @SerializedName(WEATHER_FIELD)
    @Expose
    lateinit var weather: List<Weather>
    @SerializedName(BASE_FIELD)
    @Expose
    lateinit var base: String
    @SerializedName(MAIN_FIELD)
    @Expose
    lateinit var main: Main
    @SerializedName(VISIBILITY_FIELD)
    @Expose
    var visibility = 0L
    @SerializedName(WIND_FIELD)
    @Expose
    lateinit var wind: List<Weather>
    @SerializedName(RAIN_FIELD)
    @Expose
    lateinit var rain: Rain
    @SerializedName(CLOUDS_FIELD)
    @Expose
    lateinit var clouds: Clouds
    @SerializedName(DAYTIME_FIELD)
    @Expose
    var dt = 0L
    @SerializedName(SYS_FIELD)
    @Expose
    lateinit var sys: Sys
    @SerializedName(TIMEZONE_FIELD)
    @Expose
    var timezone = 0
    @SerializedName(ID_FIELD)
    @Expose
    var id = 0L
    @SerializedName(NAME_FIELD)
    @Expose
    lateinit var name: String
    @SerializedName(COD_FIELD)
    @Expose
    var cod = 0

    constructor(coord: Coord, weather: List<Weather>, base: String, main: Main, visibility: Long, wind: List<Weather>, rain: Rain, clouds: Clouds, dt: Long, sys: Sys, timezone: Int, id: Long, name: String, cod: Int) {
        this.coord = coord
        this.weather = weather
        this.base = base
        this.main = main
        this.visibility = visibility
        this.wind = wind
        this.rain = rain
        this.clouds = clouds
        this.dt = dt
        this.sys = sys
        this.timezone = timezone
        this.id = id
        this.name = name
        this.cod = cod
    }

    constructor()
}
