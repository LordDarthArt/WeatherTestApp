package tk.lorddarthart.weathertest.app.model.forecast

import tk.lorddarthart.weathertest.app.model.forecast.sub.*

data class CurrentForecast(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val rain: Rain,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: String
)
