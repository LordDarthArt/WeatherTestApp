package tk.lorddarthart.weathertest

class Weather {
    var weather_date: Long = 0
    var weather_filterName: String? = null
    var weather_now: Double = 0.toDouble()
    var weather_city: String? = null
    var weather_high: Double = 0.toDouble()
    var weather_low: Double = 0.toDouble()
    var weather_text: String? = null
    var weather_description: String? = null
    var weather_humidity: Double = 0.toDouble()
    var weather_pressure: Double = 0.toDouble()
    var weather_rising: Long = 0
    var weather_visibility: Double = 0.toDouble()
    var weather_sunrise: String? = null
    var weather_sunset: String? = null
    var weather_d1: String? = null
    var weather_d2: String? = null
    var weather_d3: String? = null
    var weather_d4: String? = null
    var weather_d5: String? = null
    var weather_d6: String? = null
    var weather_d7: String? = null

    constructor(weather_date: Long, weather_filterName: String, weather_now: Double, weather_city: String, weather_high: Double, weather_low: Double, weather_text: String, weather_description: String, weather_humidity: Double, weather_pressure: Double, weather_rising: Long, weather_visibility: Double, weather_sunrise: String, weather_sunset: String, weather_d1: String, weather_d2: String, weather_d3: String, weather_d4: String, weather_d5: String, weather_d6: String, weather_d7: String) {
        this.weather_date = weather_date
        this.weather_filterName = weather_filterName
        this.weather_now = weather_now
        this.weather_city = weather_city
        this.weather_high = weather_high
        this.weather_low = weather_low
        this.weather_text = weather_text
        this.weather_description = weather_description
        this.weather_humidity = weather_humidity
        this.weather_pressure = weather_pressure
        this.weather_rising = weather_rising
        this.weather_visibility = weather_visibility
        this.weather_sunrise = weather_sunrise
        this.weather_sunset = weather_sunset
        this.weather_d1 = weather_d1
        this.weather_d2 = weather_d2
        this.weather_d3 = weather_d3
        this.weather_d4 = weather_d4
        this.weather_d5 = weather_d5
        this.weather_d6 = weather_d6
        this.weather_d7 = weather_d7
    }

    constructor() {

    }
}
