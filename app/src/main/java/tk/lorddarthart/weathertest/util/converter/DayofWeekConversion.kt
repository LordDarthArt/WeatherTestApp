package tk.lorddarthart.weathertest.util.converter

import tk.lorddarthart.weathertest.util.network.translator.YandexTranslate.translateToLocale

object DayofWeekConversion {
    fun convert(code: String): String {
        when (code) {
            "Mon" -> {
                return translateToLocale("Monday")
            }
            "Tue" -> {
                return translateToLocale("Tuesday")
            }
            "Wed" -> {
                return translateToLocale("Wednesday")
            }
            "Thu" -> {
                return translateToLocale("Thursday")
            }
            "Fri" -> {
                return translateToLocale("Friday")
            }
            "Sat" -> {
                return translateToLocale("Saturday")
            }
            "Sun" -> {
                return translateToLocale("Sunday")
            }
            else -> {
                return translateToLocale("Error")
            }
        }
    }
}
