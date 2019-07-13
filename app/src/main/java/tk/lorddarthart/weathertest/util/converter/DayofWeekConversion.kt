package tk.lorddarthart.weathertest

class DayofWeekConversion {
    fun convert(code: String): String {
        when (code) {
            "Mon" -> {
                return "Monday"
            }
            "Tue" -> {
                return "Tuesday"
            }
            "Wed" -> {
                return "Wednesday"
            }
            "Thu" -> {
                return "Thusday"
            }
            "Fri" -> {
                return "Friday"
            }
            "Sat" -> {
                return "Saturday"
            }
            "Sun" -> {
                return "Sunday"
            }
        }
        return ""
    }
}
