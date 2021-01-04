package tk.lorddarthart.data.local.forecast.sub

data class MainResponse(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Double,
    val temp_max: Double
)