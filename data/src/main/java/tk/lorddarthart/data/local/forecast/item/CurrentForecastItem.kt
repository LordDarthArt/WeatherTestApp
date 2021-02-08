package tk.lorddarthart.data.local.forecast.item

import tk.lorddarthart.data.local.forecast.item.sub.*
import tk.lorddarthart.data.local.forecast.sub.*
import tk.lorddarthart.data.network.forecast.sub.SysResponse

data class CurrentForecastItem(
    val coord: CoordItem,
    val weather: List<WeatherItem>,
    val base: String,
    val main: MainItem,
    val visibility: Long,
    val wind: WindItem,
    val rain: RainItem,
    val clouds: CloudsItem,
    val dt: Long,
    val sys: SysItem,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: String
)