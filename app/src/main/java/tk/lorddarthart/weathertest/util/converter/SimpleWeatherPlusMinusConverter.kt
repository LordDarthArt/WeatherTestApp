package tk.lorddarthart.weathertest.util.converter

object SimpleWeatherPlusMinusConverter {
    fun getPlusInFront(temp: Double): String {
        return if (temp > 0) {
            "+$temp"
        } else {
            temp.toString()
        }
    }
}