package tk.lorddarthart.data.local.forecast.item

import tk.lorddarthart.data.local.forecast.sub.*
import tk.lorddarthart.data.network.forecast.sub.SysResponse

data class CurrentForecastItem(
    val coord: CoordResponse,
    val weather: List<WeatherResponse>,
    val base: String,
    val main: MainResponse,
    val visibility: Long,
    val wind: WindResponse,
    val rain: RainResponse,
    val clouds: CloudsResponse,
    val dt: Long,
    val sys: SysResponse,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: String
)