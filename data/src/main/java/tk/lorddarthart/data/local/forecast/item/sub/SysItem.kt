package tk.lorddarthart.data.local.forecast.item.sub

data class SysItem(
    val type: Int?,
    val id: Int?,
    val message: Double?,
    val country: String?,
    val sunrise: Long,
    val sunset: Long
)