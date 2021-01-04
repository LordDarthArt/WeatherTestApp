package tk.lorddarthart.data.network.cities

data class CitySearchResponse(
    val cases: Any? = null,
    val code: String? = null,
    val coordinates: CoordinatesResponse? = null,
    val country_cases: Any? = null,
    val country_code: String? = null,
    val country_name: String? = null,
    val index_strings: List<String>? = null,
    val main_airport_name: Any? = null,
    val name: String? = null,
    val state_code: Any? = null,
    val type: String? = null,
    val weight: Int? = null
)