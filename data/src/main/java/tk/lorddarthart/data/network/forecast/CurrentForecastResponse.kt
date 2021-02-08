package tk.lorddarthart.data.network.forecast

import tk.lorddarthart.data.local.forecast.sub.*
import tk.lorddarthart.data.network.forecast.sub.RainResponse
import tk.lorddarthart.data.network.forecast.sub.SysResponse

data class CurrentForecastResponse(
    val coord: CoordResponse,
    val weather: List<WeatherResponse>,
    val base: String,
    val main: MainResponse,
    val visibility: Long,
    val wind: WindResponse,
    val rain: RainResponse? = null,
    val clouds: CloudsResponse,
    val dt: Long,
    val sys: SysResponse,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: String
)