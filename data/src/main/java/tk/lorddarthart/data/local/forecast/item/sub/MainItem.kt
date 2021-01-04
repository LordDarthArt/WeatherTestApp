package tk.lorddarthart.data.local.forecast.item.sub

data class MainItem(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Double,
    val temp_max: Double
)