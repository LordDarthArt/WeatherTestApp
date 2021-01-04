package tk.lorddarthart.data.local.forecast.sub

data class WeatherResponse(
    val id: Int?,
    val main: String?,
    val description: String,
    val icon: String
)