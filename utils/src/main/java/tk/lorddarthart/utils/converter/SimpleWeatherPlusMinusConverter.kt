package tk.lorddarthart.utils.converter

object SimpleWeatherPlusMinusConverter {
    fun getPlusInFront(temp: Int): String {
        return if (temp > 0.0) {
            "+$temp"
        } else {
            "$temp"
        }
    }
}