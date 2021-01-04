package tk.lorddarthart.data.network.forecast.sub

data class SysResponse(
    val type: Int?,
    val id: Int?,
    val message: Double?,
    val country: String?,
    val sunrise: Long,
    val sunset: Long
)